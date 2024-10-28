package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import com.fptu.hainxhe172366.se1730assignment.Adapter.QuizAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView homeRecyclerView;
    private QuizAdapter homeQuizAdapter;
    private DBContext dbContext;
    private List<Quiz> homeQuizList;
    private SearchView searchView;
    private EditText searchEditText;
    private ImageView logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_quiz_sets, container, false);

        bindingView(view);
        bindingAction();

        return view;
    }

    private void bindingView(View view) {
        dbContext = new DBContext(getContext());
        searchView = view.findViewById(R.id.searchEditText);
        searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        homeRecyclerView = view.findViewById(R.id.recyclerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void bindingAction() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(homeQuizAdapter != null) {
                    homeQuizAdapter.filter(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        if(homeQuizAdapter != null) {
            homeQuizAdapter.filter("");
        }
    }

    private void loadData() {
        homeQuizList = dbContext.getAllQuizzes();
        if(homeQuizAdapter == null) {
            homeQuizAdapter = new QuizAdapter(homeQuizList, getContext());
            homeRecyclerView.setAdapter(homeQuizAdapter);
        } else {
            homeQuizAdapter.updateData(homeQuizList);
        }
    }
}