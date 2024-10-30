package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fptu.hainxhe172366.se1730assignment.Adapter.AddQuestionAdapter;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionFragment extends Fragment {

    private EditText questionEditText;
    private Button btnAddQuestion;
    private RecyclerView addQuestionRecyclerView;
    private AddQuestionAdapter addQuestionAdapter;
    private int questionIdCounter = 1;
    private List<Question> questions = new ArrayList<>();
    private DBContext dbContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dbContext = new DBContext(getActivity());
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.create_question_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            onClickSaveQuestion();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        Question question = new Question(questionIdCounter, "", true);
        questions.add(question);
        questionIdCounter++;
        addQuestionAdapter.notifyDataSetChanged();
    }

    private void onClickSaveQuestion() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            long quizId = bundle.getLong("quiz_id");
            for (Question question : questions) {
                String questionContent = question.getQuestionContent();

                boolean added = dbContext.addQuestion((int) quizId, questionContent);
                if (!added) {
                    Toast.makeText(getActivity(), "Failed to add question", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            Toast.makeText(getActivity(), "Questions added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}