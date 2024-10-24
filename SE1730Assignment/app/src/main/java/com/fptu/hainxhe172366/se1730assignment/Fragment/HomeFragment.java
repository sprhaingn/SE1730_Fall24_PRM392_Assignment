package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fptu.hainxhe172366.se1730assignment.Adapter.QuizAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private DBContext dbContext;
    private Button btnLogOut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingView();
        bindingAction();
        View view = inflater.inflate(R.layout.quizmate_setsofquiz, container, false);

        return inflater.inflate(R.layout.quizmate_setsofquiz, container, false);
    }

    private void bindingAction() {

    }

    private void bindingView() {

    }
}