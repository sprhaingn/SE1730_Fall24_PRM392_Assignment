package com.fptu.hainxhe172366.se1730assignment.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionAdapter extends RecyclerView.Adapter<AddQuestionAdapter.ViewHolder> {
    private List<Question> questions;
    private List<Answer> answers;
    private Context context;

    public AddQuestionAdapter(List<Question> questions, List<Answer> answers, Context context) {
        this.questions = questions;
        this.answers = answers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quizmate_question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        Answer answer = answers.get(position);
        holder.edtQuestionContent.setText(question.getQuestionContent());
        holder.edtAnswerContent.setText(answer.getAnswerContent());
        holder.tvQuestionNumber.setText("Question " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText edtQuestionContent;
        private EditText edtAnswerContent;
        private TextView tvQuestionNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
            bindingAction();
        }

        private void bindingAction() {
        }

        private void bindingView() {
            edtQuestionContent = itemView.findViewById(R.id.edtQuestionContent);
            edtAnswerContent = itemView.findViewById(R.id.edtAnswerContent);
            tvQuestionNumber = itemView.findViewById(R.id.tvQuestionNumber);
        }

        public void setData(Question question, Answer answer) {
            edtQuestionContent.setText(question.getQuestionContent());
            edtAnswerContent.setText(answer.getAnswerContent());
        }
    }
}
