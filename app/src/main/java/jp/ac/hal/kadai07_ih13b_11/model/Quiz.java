package jp.ac.hal.kadai07_ih13b_11.model;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Represent a quiz.
 */
public class Quiz {
    /**
     * Queue of question to ask.
     */
    private Queue<Question> questions = new LinkedList<>();
    /**
     * Statistics for this quiz.
     */
    private QuizStat stat = new QuizStat();

    //#region Getters and Setters

    /**
     * Gets statistics for this quiz.
     *
     * @return Statistics for this quiz.
     */
    public QuizStat getStatistic() {
        return this.stat;
    }

    /**
     * Gets next question.
     *
     * @return Unanswered question, null if no more.
     */
    @Nullable
    public Question getQuestions() {
        return this.questions.peek();
    }
    //#endregion

    /**
     * Load question to quize.
     *
     * @param questions List of questions.
     */
    public void load(List<Question> questions) {
        this.questions = this.resetQuestion(questions);
    }

    /**
     * Report the question is answered.
     *
     * @param correct Whether the player is correct.
     */
    public void answered(boolean correct) {
        if (correct) this.stat.incrementCorrect();

        this.stat.incrementAnswered();
        this.questions.remove();
    }

    /**
     * Reset this quiz.
     *
     * @param questions List of questions.
     */
    public void reset(List<Question> questions) {
        this.load(questions);
        this.stat = new QuizStat();
    }

    /**
     * Reset question of this quiz.
     *
     * @param questions List of questions.
     * @return New queue of questions in random order.
     */
    private Queue<Question> resetQuestion(List<Question> questions) {
        Queue<Question> ret = new LinkedList<>();
        Random r = new Random();

        int c = this.stat.questCount = (int) questions.stream().count();
        while (c > 0) {
            int i = r.nextInt(c);
            ret.offer(questions.get(i));
            questions.remove(i);
            c--;
        }

        return ret;
    }
}
