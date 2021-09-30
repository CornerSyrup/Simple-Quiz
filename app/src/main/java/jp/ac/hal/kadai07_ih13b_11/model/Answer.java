package jp.ac.hal.kadai07_ih13b_11.model;

import androidx.annotation.NonNull;

public class Answer {
    private String value = "";
    private boolean correct;

    Answer() {
    }

    public Answer(String content, boolean correct) {
        this.setContent(content);
        this.setCorrect(correct);
    }

    //#region Getters and Setters
    public String getContent() {
        return this.value;
    }

    Answer setContent(String value) {
        this.value = value;
        return this;
    }

    public boolean isCorrect() {
        return this.correct;
    }

    Answer setCorrect(boolean value) {
        this.correct = value;
        return this;
    }
    //#endregion

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
