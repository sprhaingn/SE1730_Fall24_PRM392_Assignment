package com.fptu.hainxhe172366.se1730assignment.Entity;

public class Question {
    private int questionId;
    private int quizId;
    private String questionContent;
    private boolean isActive;

    public Question(int questionId, int quizId, String questionContent, boolean isActive) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionContent = questionContent;
        this.isActive = isActive;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}

