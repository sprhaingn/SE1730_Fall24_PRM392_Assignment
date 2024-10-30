package com.fptu.hainxhe172366.se1730assignment.Entity;

public class Answer {
    private int answerId;
    private int questionId;
    private String answerContent;

    public Answer(int answerId, int questionId, String answerContent) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
    }

    public Answer() {

    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}

