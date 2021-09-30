package jp.ac.hal.kadai07_ih13b_11.model;

/**
 * Represent statistics for a quiz.
 */
public class QuizStat {
    /**
     * Count of correct questions.
     */
    int corrCount;
    /**
     * Count of total question.
     */
    int questCount;

    //#region Getters and Setters

    /**
     * Gets count of correct questions.
     *
     * @return count of correct questions.
     */
    public int getCorrectCount() {
        return corrCount;
    }

    /**
     * Gets count of questions in quiz.
     *
     * @return count of questions in quiz.
     */
    public int getQuestionCount() {
        return questCount;
    }

    //#endregion

    /**
     * Increment the correct count and return it.
     *
     * @return New correct count.
     */
    int correctIncrement() {
        return ++corrCount;
    }
}
