package com.fptu.hainxhe172366.se1730assignment.Entity;

public class Quiz {
    private int quiz_id;
    private String quiz_name;
    private String addedDate;
    private boolean is_active;
    private int user_id;

    public Quiz(int quiz_id, String quiz_name, String addedDate, boolean is_active, int user_id) {
        this.quiz_id = quiz_id;
        this.quiz_name = quiz_name;
        this.addedDate = addedDate;
        this.is_active = is_active;
        this.user_id = user_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}