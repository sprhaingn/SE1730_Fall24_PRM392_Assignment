package com.fptu.hainxhe172366.se1730assignment.Activity;

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
import com.fptu.hainxhe172366.se1730assignment.MainActivity;
import com.fptu.hainxhe172366.se1730assignment.R;

public class Login extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPass;
    private Button btnLogin;
    private TextView signUpText;

    private void bindingView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        signUpText = findViewById(R.id.signupText);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onClick);
        signUpText.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            loginUsers();
        } else if (view.getId() == R.id.signupText) {
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);
        }
    }

    private void loginUsers() {
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        DBContext dbContext = new DBContext(this);

        boolean isValidUser = dbContext.validateUser(email, password);
        if (isValidUser) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }
}