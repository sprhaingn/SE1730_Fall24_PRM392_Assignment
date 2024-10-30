package com.fptu.hainxhe172366.se1730assignment.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fptu.hainxhe172366.se1730assignment.Database.DBContext;
import com.fptu.hainxhe172366.se1730assignment.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuizFragment extends Fragment {

    private Button btnAdd;
    private EditText edtName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizmate_fragment_add_quiz, container, false);

        bindingView(view);
        bindingAction();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.create_question_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            onClickAddQuiz(null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindingView(View view) {
        btnAdd = view.findViewById(R.id.btnAdd);
        edtName = view.findViewById(R.id.edtName);
    }

    private void bindingAction() {
        btnAdd.setOnClickListener(this::onClickAddQuiz);
    }

    private void onClickAddQuiz(View v) {
        String quizName = edtName.getText().toString().trim();

        if (TextUtils.isEmpty(quizName)) {
            Toast.makeText(getActivity(), "Please enter a quiz name.", Toast.LENGTH_SHORT).show();
            return;
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addedDate = formatter.format(date);

        DBContext dbContext = new DBContext(getActivity());
        long quizId = dbContext.addQuiz(quizName, addedDate, getActivity());

        if (quizId > 0) {
            Toast.makeText(getActivity(), "Quiz added successfully.", Toast.LENGTH_SHORT).show();
            edtName.setText("");

            AddQuestionFragment fragment = new AddQuestionFragment();
            Bundle bundle = new Bundle();
            bundle.putLong("quiz_id", quizId);
            fragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.addQuizContainer, fragment)
                    .commit();
        } else {
            Toast.makeText(getActivity(), "Failed to add quiz.", Toast.LENGTH_SHORT).show();
        }
    }
}
