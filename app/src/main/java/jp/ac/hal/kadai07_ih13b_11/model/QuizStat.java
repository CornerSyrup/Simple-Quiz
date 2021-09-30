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
//#endregion

    /**
     * Increment the correct count and return it.
     *
     * @return New correct count.
     */
    int incrementCorrect() {
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
