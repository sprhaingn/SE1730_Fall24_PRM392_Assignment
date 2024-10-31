package com.fptu.hainxhe172366.se1730assignment.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;
import android.text.TextWatcher;

import java.util.List;

public class AddAnswerAdapter extends RecyclerView.Adapter<AddAnswerAdapter.AddAnswerViewHolder> {
    private List<Answer> answers;
    private Context context;
    private long quizId;

    public AddAnswerAdapter(List<Answer> answers, Context context, long quizId) {
        this.answers = answers;
        this.context = context;
        this.quizId = quizId;
    }

    @NonNull
    @Override
    public AddAnswerAdapter.AddAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quizmate_answer_item, parent, false);
        return new AddAnswerAdapter.AddAnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAnswerAdapter.AddAnswerViewHolder holder, int position) {
        Answer answer = answers.get(position);
        holder.setData(answer);
        holder.tvquestionNumber.setText("Question " + (position + 1));

        DBContext dbContext = new DBContext(context);
        List<Question> questions = dbContext.getAllQuestions((int) quizId);

        if (questions.size() > position) {
            Question question = questions.get(position);
            holder.tvQuestion.setText(question.getQuestionContent());
        }
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class AddAnswerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQuestion;
        private EditText edtAnswer;
        private TextView tvquestionNumber;

        public AddAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
        }

        private void bindingView() {
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvquestionNumber = itemView.findViewById(R.id.tvQuestionNumber);
            edtAnswer = itemView.findViewById(R.id.edtAnswer);
            edtAnswer.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ((RecyclerView) itemView.getParent()).smoothScrollToPosition(position);
                    }
                }
            });
            edtAnswer.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Answer answer = answers.get(getAdapterPosition());
                    answer.setAnswerContent(charSequence.toString());

                    DBContext dbContext = new DBContext(itemView.getContext());
                    dbContext.activateQuiz((int) quizId);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        public void setData(Answer answer) {
            edtAnswer.setText(answer.getAnswerContent());
        }
    }


}
