package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.R;

public class SignUp extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtUsername;
    private EditText edtPass;
    private EditText edtConfirmPass;
    private Button btnSignUp;
    private TextView logInText;

    private void bindingView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        logInText = findViewById(R.id.loginText);
    }

    private void bindingAction() {
        btnSignUp.setOnClickListener(this::onClick);
        logInText.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) {
            signUpUser();
        } else if (view.getId() == R.id.loginText) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
    }

    private void signUpUser() {
        String email = edtEmail.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPass.getText().toString();
        String confirmPassword = edtConfirmPass.getText().toString();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        DBContext dbContext = new DBContext(this);
        boolean newUser = dbContext.addUser(username, email, password);
        if (newUser) {
            Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Sign-up failed. Email may already be in use.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }
}