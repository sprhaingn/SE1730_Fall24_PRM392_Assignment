package com.fptu.hainxhe172366.se1730assignment.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Context context;

    public AddQuestionAdapter(List<Question> questions, Context context) {
        this.questions = questions;
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
        holder.setData(question);
        holder.tvQuestionNumber.setText("Question " + (position + 1));
        holder.imgCancel.setOnClickListener(v -> onClickCancel(position));
    }

    private void onClickCancel(int position) {
        questions.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText edtQuestionContent;
        private TextView tvQuestionNumber;
        private ImageView imgCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindingView();
        }

        private void bindingView() {
            edtQuestionContent = itemView.findViewById(R.id.edtQuestionContent);
            tvQuestionNumber = itemView.findViewById(R.id.tvQuestionNumber);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            edtQuestionContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Question question = questions.get(getAdapterPosition());
                    question.setQuestionContent(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        public void setData(Question question) {
            edtQuestionContent.setText(question.getQuestionContent());
        }
    }
}