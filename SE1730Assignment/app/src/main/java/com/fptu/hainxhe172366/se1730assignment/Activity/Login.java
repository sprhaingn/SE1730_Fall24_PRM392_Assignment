package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Login extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPass;
    private Button btnLogin;
    private TextView signUpText;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quizmate_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
        autoLogin();
    }

    private void bindingView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        signUpText = findViewById(R.id.signupText);
        rememberMeCheckBox = findViewById(R.id.rememberMe);
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
        int userId = dbContext.validateUser(email, password);

        if (userId != -1) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            if (rememberMeCheckBox.isChecked()) {
                saveUserId(userId);
                saveLoginCredentials(email, password);
            } else {
                saveUserId(userId);
            }
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserId(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }

    private void saveLoginCredentials(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("rememberMe", true);
        editor.apply();
    }

    private void autoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            String email = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            DBContext dbContext = new DBContext(this);
            int userId = dbContext.validateUser(email, password);
            if (userId != -1) {
                saveUserId(userId);
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
                finish();
            }
        }
    }

}