// package com.fptu.hainxhe172366.se1730assignment;

// <<<<<<< Updated upstream:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/AddQuiz.java
// =======
// import android.annotation.SuppressLint;
// import android.content.Intent;
// >>>>>>> Stashed changes:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/MainActivity.java~
// import android.os.Bundle;
// import android.view.MenuItem;

// import androidx.activity.EdgeToEdge;
// import androidx.annotation.NonNull;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.core.graphics.Insets;
// import androidx.core.view.ViewCompat;
// import androidx.core.view.WindowInsetsCompat;

// <<<<<<< Updated upstream:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/AddQuiz.java
// public class AddQuiz extends AppCompatActivity {

// =======
// import com.fptu.hainxhe172366.se1730assignment.Activity.AddQuiz;
// import com.google.android.material.bottomnavigation.BottomNavigationView;

// public class MainActivity extends AppCompatActivity {

//     private BottomNavigationView navigate;

//     private void bindingView() {
//         navigate = findViewById(R.id.bottomNavigationView);
//     }

// //    private void bindingAction() {
// //        navigate.setOnItemSelectedListener(this::onClick);
// //    }
// //
// //    private boolean onClick(@NonNull MenuItem menuItem) {
// //        switch (menuItem.getItemId()) {
// //            case R.id.navigation_home:
// //                // Handle navigation to Home (if needed)
// //                return true;
// //
// //            case R.id.navigation_add:
// //                // Start the AddQuiz activity
// //                Intent intent = new Intent(MainActivity.this, AddQuiz.class);
// //                startActivity(intent);
// //                return true;
// //
// //            case R.id.navigation_collection:
// //                // Handle navigation to Collection (if needed)
// //                return true;
// //
// //            default:
// //                return false;
// //        }
// //    }

//     @SuppressLint("MissingInflatedId")
// >>>>>>> Stashed changes:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/MainActivity.java~
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         EdgeToEdge.enable(this);
// <<<<<<< quang
// <<<<<<< Updated upstream:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/AddQuiz.java
//         setContentView(R.layout.activity_add_quiz);
// =======
//         setContentView(R.layout.quizmate_main);
// >>>>>>> Stashed changes:SE1730Assignment/app/src/main/java/com/fptu/hainxhe172366/se1730assignment/MainActivity.java~
// =======
//         setContentView(R.layout.quizmate_add_quiz);
// >>>>>>> main
//         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//             Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//             return insets;
//         });
//         bindingView();
//         bindingAction();
//     }
// }