package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import com.fptu.hainxhe172366.se1730assignment.Adapter.QuizAdapter;
import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private DBContext dbContext;
    private Button btnLogOut;
    private List<Quiz> quizList;
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
        logoutBtn = view.findViewById(R.id.logoutBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void bindingAction() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(quizAdapter != null) {
                    quizAdapter.filter(newText);
                }
                return true;
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(quizAdapter != null) {
                quizAdapter.filter(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        if(quizAdapter != null) {
            quizAdapter.filter("");
        }
    }

    private void loadData() {
        List<Quiz> quizList = dbContext.getAllQuizzes();
        if(quizAdapter == null) {
            quizAdapter = new QuizAdapter(quizList, getContext());
            recyclerView.setAdapter(quizAdapter);
        } else {
            quizAdapter.updateData(quizList);
        }
    }
}