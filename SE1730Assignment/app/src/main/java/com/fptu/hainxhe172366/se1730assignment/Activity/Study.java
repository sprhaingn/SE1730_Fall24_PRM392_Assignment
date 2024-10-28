package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fptu.hainxhe172366.se1730assignment.R;

public class Study extends AppCompatActivity {
    private ImageView btnAdd;
    private Button btnStudy;
    private Toolbar toolbar;

    private void bindingView() {
        btnAdd = findViewById(R.id.btnAdd);
        btnStudy = findViewById(R.id.btnStudy);
        toolbar = findViewById(R.id.toolbar);
    }

    private void bindingAction() {
        btnAdd.setOnClickListener(this::onClick);
        btnStudy.setOnClickListener(this::onClick);
        toolbar.setNavigationOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            Intent intent = new Intent(this, Study.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btnStudy) {

        } else if (view.getId() == R.id.toolbar) {
            finish();
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
        setContentView(R.layout.quizmate_study_quiz);
        setSupportActionBar(toolbar);
        bindingView();
        bindingAction();
    }
}