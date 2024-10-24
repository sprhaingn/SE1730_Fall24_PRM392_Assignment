package com.fptu.hainxhe172366.se1730assignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.fptu.hainxhe172366.se1730assignment.Fragment.HomeFragment;
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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }
    }

    private void bindingView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

    }

    private void bindingAction() {
        bottomNavigationView.setOnItemSelectedListener(this::onClickNavigation);
    }

    private boolean onClickNavigation(MenuItem menuItem) {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.navigation_add) {
                startActivity(new Intent(this, AddQuiz.class));
                return true;
            } else if (itemId == R.id.navigation_myset) {
//                loadFragment(new MenuFragment());
                return true;
            }
            return false;
        });
        return false;
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}