package jp.ac.hal.kadai07_ih13b_11;

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
    private Quiz model;

    private TextView txtQue;
    private RadioGroup rbgAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        try {
            this.model = new Quiz(new QuizContentParser().parse(this.getResources().getXml(R.xml.quiz)));
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), "fail to obtain quiz content", Toast.LENGTH_LONG).show();
            // Log.e("Quiz", "QuizActivity onCreate: fail to read/parse quiz xml", ex);
        }

        this.txtQue = this.findViewById(R.id.question);
        this.rbgAns = this.findViewById(R.id.answer_group);

        this.updateQuestion(this.model.getStatistic(), this.model.getQuestions());
        ((TextView) this.findViewById(R.id.question_count)).setText(Integer.toString(this.model.getStatistic().getQuestionCount()));
    }

    public void answerQuestion(View answerButton) {
        int focusIdx = this.rbgAns.indexOfChild(this.findViewById(this.rbgAns.getCheckedRadioButtonId()));
        boolean correct = this.model.getQuestions().getAnswers()[focusIdx].isCorrect();
        
        this.model.answered(correct);

        Question q = this.model.getQuestions();
        if (q != null) {
            this.updateQuestion(this.model.getStatistic(), q);
        } else {
            this.updateStat(this.model.getStatistic());
        }
    }

    public void resetChoice(View resetButton) {
        this.rbgAns.clearCheck();
    }

    private void updateQuestion(QuizStat stat, Question quest) {
        this.updateStat(stat);
        this.updateQuestion(quest);
    }

    private void updateStat(QuizStat stat) {
        ((TextView) this.findViewById(R.id.correct_count)).setText(Integer.toString(stat.getCorrectCount()));
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