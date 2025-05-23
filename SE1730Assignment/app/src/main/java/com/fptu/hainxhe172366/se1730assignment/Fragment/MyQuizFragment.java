package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.content.Context;
import android.content.Intent;
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

import com.fptu.hainxhe172366.se1730assignment.Activity.Login;
import com.fptu.hainxhe172366.se1730assignment.Adapter.QuizAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;


public class MyQuizFragment extends Fragment {
    private RecyclerView myQuizRecyclerView;
    private QuizAdapter myQuizAdapter;
    private DBContext dbContext;
    private List<Quiz> myQuizList;
    private ImageView btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_my_set, container, false);

        bindingView(view);
        bindingAction();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        if(myQuizAdapter != null) {
            myQuizAdapter.filter("");
        }
    }

    private void bindingView(View view) {
        dbContext = new DBContext(getContext());
        myQuizRecyclerView = view.findViewById(R.id.myQuizRecyclerView);
        myQuizRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnLogout = view.findViewById(R.id.logoutBtn);
    }

    private void bindingAction() {
        btnLogout.setOnClickListener(this::onClickLogOut);
    }

    private void onClickLogOut(View view) {
        Log.d("MyQuizFragment", "onClickLogOut");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        if (userId == -1) {
            return;
        }

        myQuizList = dbContext.getAllMyQuizzes(userId);
        if (myQuizList == null || myQuizList.isEmpty()) {
            return;
        }

        myQuizAdapter = new QuizAdapter(myQuizList, getContext());
        myQuizRecyclerView.setAdapter(myQuizAdapter);
    }


}