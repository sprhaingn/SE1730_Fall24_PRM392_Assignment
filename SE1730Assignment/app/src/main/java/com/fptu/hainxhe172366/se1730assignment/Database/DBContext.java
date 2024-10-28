package com.fptu.hainxhe172366.se1730assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fptu.hainxhe172366.se1730assignment.Entity.Quiz;
import com.fptu.hainxhe172366.se1730assignment.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "QuizMate";

    private static final String TB_USER = "user";
    private static final String TB_QUIZ = "quiz";
    private static final String TB_QUESTION = "question";
    private static final String TB_ANSWER = "answer";

    private static final String QUIZ_ID = "quiz_id";
    private static final String QUIZ_NAME = "quiz_name";
    private static final String QUIZ_ADDED_DATE = "addedDate";
    private static final String QUIZ_AUTHOR = "user_id";

    private static final String CREATE_TB_USER =
            "CREATE TABLE user (" +
                    "    user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    user_name NVARCHAR(50) NOT NULL, " +
                    "    user_email VARCHAR(50) NOT NULL UNIQUE, " +
                    "    user_password VARCHAR(50) NOT NULL, " +
                    "    is_active BIT DEFAULT 0 NOT NULL" +
                    ");";

    private static final String CREATE_TB_QUIZ =
            "CREATE TABLE quiz (" +
                    "    quiz_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    quiz_name NVARCHAR(100) NOT NULL, " +
                    "    addedDate DATETIME NOT NULL, " +
                    "    is_active BIT DEFAULT 0 NOT NULL, " +
                    "    user_id INTEGER REFERENCES user(user_id)" +
                    ");";

    private static final String CREATE_TB_QUESTION =
            "CREATE TABLE question (" +
                    "    question_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    quiz_id INTEGER REFERENCES quiz(quiz_id), " +
                    "    question_content VARCHAR(500) NOT NULL, " +
                    "    is_active BIT DEFAULT 0 NOT NULL" +
                    ");";

    private static final String CREATE_TB_ANSWER =
            "CREATE TABLE answer (" +
                    "    answer_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    question_id INTEGER REFERENCES question(question_id) NOT NULL, " +
                    "    answer_content NVARCHAR(4000) NOT NULL, " +
                    "    is_true BIT DEFAULT 0 NOT NULL" +
                    ");";

    public DBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_USER);
        db.execSQL(CREATE_TB_QUIZ);
        db.execSQL(CREATE_TB_QUESTION);
        db.execSQL(CREATE_TB_ANSWER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        if (oldVer <= 1 && newVer >= 2) {
            db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TB_QUIZ);
            db.execSQL("DROP TABLE IF EXISTS " + TB_QUESTION);
            db.execSQL("DROP TABLE IF EXISTS " + TB_ANSWER);
        }
        onCreate(db);
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_QUIZ + " WHERE is_active = 1", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(QUIZ_ID));
                String quizName = cursor.getString(cursor.getColumnIndexOrThrow(QUIZ_NAME));
                String addedDate = cursor.getString(cursor.getColumnIndexOrThrow(QUIZ_ADDED_DATE));
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow(QUIZ_AUTHOR));
                Quiz quiz = new Quiz(id, quizName, addedDate, true, userId);
                quizzes.add(quiz);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return quizzes;
    }

    public boolean addUser(String userName, String userEmail, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", userName);
        values.put("user_email", userEmail);
        values.put("user_password", userPassword);
        values.put("is_active", 1);

        long result = db.insert(TB_USER, null, values);
        db.close();
        return result != -1;
    }

    public int validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT user_id FROM user WHERE user_email = ? AND user_password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }

    public boolean addQuiz(String quizName, String addedDate, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quiz_name", quizName);
        values.put("addedDate", addedDate);
        values.put("is_active", 1);

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        if (userId == -1) {
            db.close();
            return false;
        }

        values.put("user_id", userId);
        long result = db.insert(TB_QUIZ, null, values);
        db.close();
        return result != -1;
    }

    public User getUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user WHERE user_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
        }
        cursor.close();
        return user;
    }

    public List<Quiz> getAllMyQuizzes(int userId) {
        List<Quiz> quizzes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_QUIZ + " WHERE is_active = 1 AND user_id = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(QUIZ_ID));
                String quizName = cursor.getString(cursor.getColumnIndexOrThrow(QUIZ_NAME));
                String addedDate = cursor.getString(cursor.getColumnIndexOrThrow(QUIZ_ADDED_DATE));
                Quiz quiz = new Quiz(id, quizName, addedDate, true, userId);
                quizzes.add(quiz);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return quizzes;
    }

}
