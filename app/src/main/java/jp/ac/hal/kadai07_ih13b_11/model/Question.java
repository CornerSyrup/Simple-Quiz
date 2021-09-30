package jp.ac.hal.kadai07_ih13b_11.model;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represent a quiz question.
 */
public class Question {
    /**
     * Content of the question.
     */
    private String value = "";
    /**
     * Possible answers of this answer.
     */
    private Answer[] answers;
    /**
     * Correct answer.
     */
    private Answer correct;

    /**
     * Instantiate a new question.
     */
    Question(String value) {
        this.value = value;

        this.answers = new Answer[0];
    }

    /**
     * Instantiate a new question.
     *
     * @param value   Content of the question.
     * @param answers Possible answers.
     */
    Question(String value, Answer[] answers) {
        this.value = value;
        this.setAnswers(answers);
    }

    //#region Getters and Setters

    /**
     * Gets the content of the question.
     *
     * @return
     */
    public String getQuestion() {
        return this.value;
    }

    /**
     * Gets all possible answers.
     *
     * @return
     */
    public Answer[] getAnswers() {
        return this.answers;
    }

    /**
     * Sets possible answers.
     *
     * @param values
     * @return This question object.
     */
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

    /**
     * Adds a new answer to possible answer set.
     *
     * @param value New answer.
     * @return This question object.
     */
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

    /**
     * Convert into string representation.
     *
     * @return Question in string representation.
     */
    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
