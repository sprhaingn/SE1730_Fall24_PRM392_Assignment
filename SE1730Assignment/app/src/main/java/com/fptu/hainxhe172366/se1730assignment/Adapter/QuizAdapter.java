package com.fptu.hainxhe172366.se1730assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Activity.Study;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.Entity.User;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private List<Quiz> quizzes;
    private List<Quiz> filteredQuizzes;
    private Context context;

    public QuizAdapter(List<Quiz> quizzes, Context context) {
        this.quizzes = quizzes;
        this.context = context;
        this.filteredQuizzes = new ArrayList<Quiz>();
    }

    public void updateData(List<Quiz> newQuizList) {
        this.quizzes = newQuizList;
        this.filteredQuizzes = new ArrayList<>(newQuizList);
        notifyDataSetChanged();
    }

    public void filter(String text) {
        filteredQuizzes.clear();
        if (text.isEmpty()) {
            filteredQuizzes.addAll(quizzes);
        } else {
            text = text.toLowerCase();
            for (Quiz item : quizzes) {
                if (item.getQuiz_name().toLowerCase().contains(text)) {
                    filteredQuizzes.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quizmate_quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Quiz quiz = filteredQuizzes.get(position);
        holder.setData(quiz);
    }

    @Override
    public int getItemCount() {
        return filteredQuizzes.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private TextView quizName;
        private TextView authorName;
        private TextView termsCount;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
            bidningAction();
        }

        private void bidningAction() {
            itemView.setOnClickListener(this::onClickStudy);
        }

        private void onClickStudy(View view) {
            Intent intent = new Intent(context, Study.class);
            Quiz quiz = quizzes.get(getAdapterPosition());
            intent.putExtra("quiz_id", quiz.getQuiz_id());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }

        private void bindingView() {
            quizName = itemView.findViewById(R.id.titleText);
            authorName = itemView.findViewById(R.id.username);
        }

        public void setData(Quiz quiz) {
            quizName.setText(quiz.getQuiz_name());
            DBContext dbContext = new DBContext(context);
            User user = dbContext.getUser(quiz.getUser_id());
            if (user != null) {
                authorName.setText(user.getUsername());
            } else {
                authorName.setText("Unknown Author");
            }
        }
    }
}
