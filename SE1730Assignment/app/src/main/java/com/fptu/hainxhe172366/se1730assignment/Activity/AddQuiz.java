package com.fptu.hainxhe172366.se1730assignment.Activity;

import static java.time.LocalDateTime.now;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.R;

public class AddQuiz extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtName;

    private void bindingView() {
        btnAdd = findViewById(R.id.btnAdd);
        edtName = findViewById(R.id.edtName);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void bindingAction() {
        btnAdd.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            addQuiz();
        }
    }

    private void addQuiz() {
        String quizName = edtName.getText().toString().trim();

        if (quizName.isEmpty()) {
            Toast.makeText(this, "Please enter a quiz name.", Toast.LENGTH_SHORT).show();
            return;
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addedDate = formatter.format(date);

        DBContext dbContext = new DBContext(this);

        boolean newQuiz = dbContext.addQuiz(quizName, addedDate, this);

        if (newQuiz) {
            Toast.makeText(this, "Quiz added successfully.", Toast.LENGTH_SHORT).show();
            edtName.setText("");
        } else {
            Toast.makeText(this, "Failed to add quiz.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quizmate_add_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }
}