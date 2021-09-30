package jp.ac.hal.kadai07_ih13b_11.model;

import java.io.Serializable;

/**
 * Represent statistics for a quiz.
 */
public class QuizStat implements Serializable {
    /**
     * Count of correct questions.
     */
    int corrCount;
    /**
     * Count of correct in a round.
     */
    int roundCorr;
    /**
     * Count of total question.
     */
    int questCount;
    /**
     * Count of answered question.
     */
    int ansCount;

    //#region Getters and Setters

    /**
     * Gets count of correct questions.
     *
     * @return count of correct questions.
     */
    public int getCorrectCount() {
        return this.corrCount;
    }

    /**
     * Gets count of questions in quiz.
     *
     * @return count of questions in quiz.
     */
    public int getQuestionCount() {
        return this.questCount;
    }

    /**
     * Gets count of answered questions for now.
     *
     * @return count of answered questions for now.
     */
    public int getAnsweredCount() {
        return this.ansCount;
    }

    /**
     * Gets count of corrects and reset round.
     *
     * @return count of corrects in round.
     */
    public int getRountCorrectCount() {
        int i = this.roundCorr;
        this.roundCorr = 0;
        return i;
    }
//#endregion

    /**
     * Increment the correct count and return it.
     *
     * @return New correct count.
     */
    int incrementCorrect() {
        this.roundCorr++;
        return ++this.corrCount;
    }

    /**
     * Increment the answered count and return it.
     *
     * @return New answered count.
     */
    int incrementAnswered() {
        return ++this.ansCount;
    }
}
