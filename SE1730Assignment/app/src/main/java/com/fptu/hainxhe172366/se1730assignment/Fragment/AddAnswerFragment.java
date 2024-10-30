package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Adapter.AddAnswerAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.ArrayList;
import java.util.List;

public class AddAnswerFragment extends Fragment {
    private RecyclerView addAnswerRecyclerView;
    private AddAnswerAdapter addAnswerAdapter;
    private List<Answer> answers = new ArrayList<>();
    private DBContext dbContext;
    private long quizId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbContext = new DBContext(getActivity());
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            quizId = bundle.getLong("quiz_id");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.create_question_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            onClickSaveAnswer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickSaveAnswer() {
        for (Answer answer : answers) {
            int questionId = answer.getQuestionId();
            String answerContent = answer.getAnswerContent();
            dbContext.addAnswer(questionId, answerContent);
        }
        Toast.makeText(getContext(), "Answers added successfully!", Toast.LENGTH_SHORT).show();

        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_add_answer, container, false);
        bindingView(view);
        bindingAction();

        Log.d("AddAnswerFragment", "Quiz ID: " + quizId);

        List<Question> questions = dbContext.getAllQuestions((int) quizId);
        if (questions != null) {
            for (Question question : questions) {
                Log.d("AddAnswerFragment", "Question: " + question.getQuestionContent());

                Answer answer = new Answer();
                answer.setQuestionId(question.getQuestionId());
                answers.add(answer);
            }
        }

        addAnswerAdapter.notifyDataSetChanged();

        return view;
    }

    private void bindingAction() {

    }

    private void bindingView(View view) {
        addAnswerRecyclerView = view.findViewById(R.id.addAnswerRecyclerView);
        addAnswerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addAnswerAdapter = new AddAnswerAdapter(answers, getContext(), quizId);
        addAnswerRecyclerView.setAdapter(addAnswerAdapter);
    }
}