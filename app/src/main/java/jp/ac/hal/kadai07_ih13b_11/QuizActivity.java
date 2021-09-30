package jp.ac.hal.kadai07_ih13b_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.hal.kadai07_ih13b_11.model.Answer;
import jp.ac.hal.kadai07_ih13b_11.model.Question;
import jp.ac.hal.kadai07_ih13b_11.model.Quiz;
import jp.ac.hal.kadai07_ih13b_11.model.QuizContentParser;
import jp.ac.hal.kadai07_ih13b_11.model.QuizStat;

public class QuizActivity extends AppCompatActivity {
    private Quiz model = new Quiz();

    private TextView txtQue;
    private RadioGroup rbgAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.txtQue = this.findViewById(R.id.question);
        this.rbgAns = this.findViewById(R.id.answer_group);

        try {
            this.model.load(new QuizContentParser().parse(this.getResources().getXml(R.xml.quiz)));
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), "fail to obtain quiz content", Toast.LENGTH_LONG).show();
            // Log.e("Quiz", "QuizActivity onCreate: fail to read/parse quiz xml", ex);
        }

        this.updateQuiz(this.model.getStatistic(), this.model.getQuestions());
    }

    public void answerQuestion(View answerButton) {
        // already ended
        Question q = this.model.getQuestions();
        if (q == null) {
            this.endQuiz();
            return;
        }

        QuizStat stat = this.model.getStatistic();

        int i = this.rbgAns.getCheckedRadioButtonId();
        if (i < 0) {
            Toast.makeText(this.getApplicationContext(), this.getText(R.string.blank_answer_warn), Toast.LENGTH_SHORT).show();
            return;
        }

        int focusIdx = this.rbgAns.indexOfChild(this.findViewById(i));
        boolean correct = q.getAnswers()[focusIdx].isCorrect();
        this.model.answered(correct);

        // each 3 question
        if (stat.getAnsweredCount() % 3 == 0) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(ResultActivity.DOING_GOOD, stat.getRountCorrectCount() >= 2);
            this.startActivity(intent);
        }

        // just finish last question
        q = this.model.getQuestions();
        if (q == null) {
            this.endQuiz();
            return;
        }

        this.updateQuiz(stat, q);
    }

    public void resetQuiz(View resetButton) {
        try {
            this.model.reset(new QuizContentParser().parse(this.getResources().getXml(R.xml.quiz)));
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), "fail to obtain quiz content", Toast.LENGTH_LONG).show();
            // Log.e("Quiz", "QuizActivity onCreate: fail to read/parse quiz xml", ex);
        }

        this.updateQuiz(this.model.getStatistic(), this.model.getQuestions());
    }

    private void endQuiz() {
        this.updateStat(this.model.getStatistic());
    }

    private void updateQuiz(QuizStat stat, Question quest) {
        this.updateStat(stat);
        this.updateQuestion(quest);
    }

    private void updateStat(QuizStat stat) {
        ((TextView) this.findViewById(R.id.correct_count)).setText(Integer.toString(stat.getCorrectCount()));
        ((TextView) this.findViewById(R.id.question_count)).setText(Integer.toString(this.model.getStatistic().getAnsweredCount()));
    }

    private void updateQuestion(Question question) {
        Answer[] ans = question.getAnswers();
        this.txtQue.setText(question.getQuestion());
        this.rbgAns.removeAllViews();

        for (Answer an : ans) {
            RadioButton r = new RadioButton(this.getApplicationContext());
            r.setText(an.getContent());
            this.rbgAns.addView(r, new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}