package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fptu.hainxhe172366.se1730assignment.Adapter.QuizAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Question;
import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;


public class MyQuizFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private DBContext dbContext;
    private List<Quiz> quizList;
    private ImageView btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_my_set, container, false);

        bindingView(view);
        bindingAction();

        return view;
    }

    private void bindingView(View view) {
        dbContext = new DBContext(getContext());
        recyclerView = view.findViewById(R.id.myQuizRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnLogout = view.findViewById(R.id.logoutBtn);
    }

    private void bindingAction() {
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        if (userId == -1) {
            return;
        }

        List<Quiz> quizList = dbContext.getAllMyQuizzes(userId);
        if (quizList == null || quizList.isEmpty()) {
            return;
        }

        if (quizAdapter == null) {
            quizAdapter = new QuizAdapter(quizList, getContext());
            recyclerView.setAdapter(quizAdapter);
        } else {
            quizAdapter.updateData(quizList);
        }
    }


}