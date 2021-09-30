package jp.ac.hal.kadai07_ih13b_11.model;

import androidx.annotation.NonNull;

/**
 * Represent a quiz answer.
 */
public class Answer {
    /**
     * Content of the answer.
     */
    private String value = "";
    /**
     * Whether this answer is correct.
     */
    private boolean correct;

    /**
     * Initiate a new answer.
     */
    Answer() {
    }

    /**
     * Initiate a new answer.
     *
     * @param content Content of the answer.
     * @param correct Whether this answer is correct.
     */
    public Answer(String content, boolean correct) {
        this.value = content;
        this.correct = correct;
    }

    //#region Getters and Setters

    /**
     * Gets the answer's content.
     *
     * @return
     */
    public String getContent() {
        return this.value;
    }

    /**
     * Gets whether this answer is correct.
     *
     * @return
     */
    public boolean isCorrect() {
        return this.correct;
    }
    //#endregion

    /**
     * Convert into string representation.
     *
     * @return Answer in string representation.
     */
    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
