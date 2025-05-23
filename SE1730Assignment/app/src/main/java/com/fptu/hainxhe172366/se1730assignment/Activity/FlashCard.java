package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Answer;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;

public class FlashCard extends AppCompatActivity {
    private ImageView btnExit;
    private ImageView btnNext;
    private ImageView btnPrevious;
    private TextView tvFlashCard;
    private DBContext dbContext;
    private List<Question> questions;
    private List<Answer> answers;
    private int currentQuestionIndex = 0;
    private boolean isShowingAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Intent intent = getIntent();
        int quizId = intent.getIntExtra("quiz_id", -1);

        setContentView(R.layout.quizmate_flash_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();

        dbContext = new DBContext(this);
        questions = dbContext.getAllQuestionsByQuizId(quizId);
        displayCurrentQuestion();
    }

    private void bindingAction() {
        btnNext.setOnClickListener(this::displayNextQuestion);
        btnPrevious.setOnClickListener(this::displayPreviousQuestion);
        btnExit.setOnClickListener((this::exitFlashCard));
        findViewById(R.id.flashCardContainer).setOnClickListener(this::displayAnswer);
    }

    private void displayPreviousQuestion(View view) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayCurrentQuestion();
            Log.d("FlashCard", "Current question index: " + currentQuestionIndex);
        } else {
        }
    }

    private void displayNextQuestion(View view) {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayCurrentQuestion();
            Log.d("FlashCard", "Current question index: " + currentQuestionIndex);
        } else {
        }
    }

    private void exitFlashCard(View view) {
        finish();
    }

    private void bindingView() {
        btnExit = findViewById(R.id.btnExit);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        tvFlashCard = findViewById(R.id.tvFlashCard);
    }

    private void displayAnswer(View view) {
        if (questions != null && !questions.isEmpty()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (isShowingAnswer) {
                tvFlashCard.setText(currentQuestion.getQuestionContent());
                isShowingAnswer = false;
            } else {
                int questionId = currentQuestion.getQuestionId();
                List<Answer> answers = dbContext.getAnswersByQuestionId(questionId);
                if (answers != null && !answers.isEmpty()) {
                    Answer answer = answers.get(0);
                    tvFlashCard.setText(answer.getAnswerContent());
                    isShowingAnswer = true;
                } else {
                    tvFlashCard.setText("No answer found");
                }
            }
        } else {
            tvFlashCard.setText("No questions found");
        }
    }

    private void displayCurrentQuestion() {
        if (questions != null && !questions.isEmpty()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            tvFlashCard.setText(currentQuestion.getQuestionContent());
            isShowingAnswer = false;
        } else {
            tvFlashCard.setText("No questions found");
        }
    }
}