package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.fptu.hainxhe172366.se1730assignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quizmate_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

    }

    private void bindingAction() {
        bottomNavigationView.setOnItemSelectedListener(this::onClickNavigation);
    }

    private boolean onClickNavigation(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_add:
                return true;
            case R.id.navigation_myset:
                return true;
            default:
                return false;
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, fragment)
                .commit();
    }
}