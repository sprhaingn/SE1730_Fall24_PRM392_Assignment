package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fptu.hainxhe172366.se1730assignment.Adapter.StudyQuestionAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Study extends AppCompatActivity {
    private Button btnStudy;
    private Toolbar toolbar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizmate_study_quiz);

        bindingView();
        bindingAction();

        Intent intent = getIntent();
        int quizId = intent.getIntExtra("quiz_id", -1);

        if (quizId != -1) {
            setupRecyclerView(quizId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindingView() {
        btnStudy = findViewById(R.id.btnStudy);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.studyRecyclerView);
    }

    private void bindingAction() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnStudy.setOnClickListener(this::onClickStudy);
    }

    private void onClickStudy(View view) {
        Intent intent = getIntent();
        int quizId = intent.getIntExtra("quiz_id", -1);

        if (quizId != -1) {
            Intent flashCardIntent = new Intent(this, FlashCard.class);
            flashCardIntent.putExtra("quiz_id", quizId);
            startActivity(flashCardIntent);
        }
    }

    private void setupRecyclerView(int quizId) {
        DBContext dbContext = new DBContext(this);
        List<Question> questions = dbContext.getAllQuestionsByQuizId(quizId);
        if (!questions.isEmpty()) {
            btnStudy.setText(questions.get(0).getQuestionContent());
        }else{
            btnStudy.setText("No questions found");
        }
        List<Answer> allAnswers = new ArrayList<>();
        for (Question question : questions) {
            List<Answer> answers = dbContext.getAllAnswersByQuestionId(question.getQuestionId());
            allAnswers.addAll(answers);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudyQuestionAdapter adapter = new StudyQuestionAdapter(questions, allAnswers, this);
        recyclerView.setAdapter(adapter);
    }


}