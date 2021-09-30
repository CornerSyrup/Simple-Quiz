package jp.ac.hal.kadai07_ih13b_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import jp.ac.hal.kadai07_ih13b_11.model.QuizStat;

public class FinalActivity extends AppCompatActivity {
    public static final String RESULT_STAT = "result_stat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        QuizStat stat = (QuizStat) this.getIntent().getExtras().getSerializable(FinalActivity.RESULT_STAT);

        ((TextView) this.findViewById(R.id.result_text)).setText(
                String.format(
                        this.getText(R.string.farewell_result_format).toString(),
                        stat.getCorrectCount(), stat.getQuestionCount(), Math.round(stat.getCorrectCount() / stat.getQuestionCount())
                )
        );
    }

    public void exitQuiz(View finalActivity) {
        this.finishAffinity();
    }
}