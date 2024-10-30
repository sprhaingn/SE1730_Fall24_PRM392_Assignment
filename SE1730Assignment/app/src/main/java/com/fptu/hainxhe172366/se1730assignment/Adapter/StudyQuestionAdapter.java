package com.fptu.hainxhe172366.se1730assignment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;

public class StudyQuestionAdapter extends RecyclerView.Adapter<StudyQuestionAdapter.StudyQuestionViewHolder> {
    private Context context;
    private List<Question> questions;
    private List<Answer> answers;

    public StudyQuestionAdapter(List<Question> questions, List<Answer> answers, Context context) {
        this.questions = questions;
        this.answers = answers;
        this.context = context;
    }

    @NonNull
    @Override
    public StudyQuestionAdapter.StudyQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.quizmate_study_question_item, null);
        return new StudyQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudyQuestionAdapter.StudyQuestionViewHolder holder, int position) {
        if (questions != null && !questions.isEmpty() && position < questions.size()) {
            Question question = questions.get(position);
            Answer answer = answers != null && !answers.isEmpty() && position < answers.size() ? answers.get(position) : null;
            holder.setData(question, answer);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class StudyQuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewQuestion;
        private TextView textViewAnswer;

        public StudyQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
            bindingAction();
        }

        private void bindingAction() {
        }

        private void bindingView() {
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            textViewAnswer = itemView.findViewById(R.id.textViewAnswer);
        }

        public void setData(Question question, Answer answer) {
            textViewQuestion.setText(question.getQuestionContent());
            textViewAnswer.setText(answer != null ? answer.getAnswerContent() : "");
        }
    }
}
