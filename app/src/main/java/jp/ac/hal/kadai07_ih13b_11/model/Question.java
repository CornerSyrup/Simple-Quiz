package jp.ac.hal.kadai07_ih13b_11.model;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private String value = "";
    private Answer[] answers;
    private Answer correct;

    Question() {
        this.answers = new Answer[0];
    }

    Question(String value, Answer[] answers) {
        this.setQuestion(value);
        this.setAnswers(answers);
    }

    //#region Getters and Setters
    public String getQuestion() {
        return this.value;
    }

    Question setQuestion(String value) {
        this.value = value;
        return this;
    }

    public Answer[] getAnswers() {
        return this.answers;
    }

    Question setAnswers(Answer[] values) {
        this.answers = values;

        for (Answer a : this.answers) {
            if (a.isCorrect()) {
                this.correct = a;
                break;
            }
        }

        return this;
    }

    Question addAnswer(Answer value) {
        int i = this.answers.length;
        this.answers = Arrays.copyOf(this.answers, i + 1);
        this.answers[i] = value;

        if (this.correct == null && value.isCorrect()) {
            this.correct = value;
        }

        return this;
    }
    //#endregion

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
