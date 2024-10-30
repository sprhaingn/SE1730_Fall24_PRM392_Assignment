package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fptu.hainxhe172366.se1730assignment.Activity.AddQuiz;
import com.fptu.hainxhe172366.se1730assignment.Adapter.AddQuestionAdapter;
import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionFragment extends Fragment {

    private EditText questionEditText;
    private Button btnAddQuestion;
    private RecyclerView addQuestionRecyclerView;
    private AddQuestionAdapter addQuestionAdapter;
    private int questionIdCounter = 1;
    private List<Question> questions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_add_question, container, false);
        bindingView(view);
        bindingAction();
        addQuestion(view);
        return view;
    }

    private void bindingView(View view) {
        addQuestionRecyclerView = view.findViewById(R.id.addQuestionRecyclerView);
        addQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addQuestionAdapter = new AddQuestionAdapter(questions, getContext());
        addQuestionRecyclerView.setAdapter(addQuestionAdapter);
        btnAddQuestion = view.findViewById(R.id.btnAddQuestion);
    }

    private void bindingAction() {
        btnAddQuestion.setOnClickListener(this::addQuestion);
    }

    private void addQuestion(View view) {
        Question question = new Question(questionIdCounter, "", false);
        questions.add(question);
        questionIdCounter++;
        addQuestionAdapter.notifyDataSetChanged();
    }
}