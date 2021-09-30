package jp.ac.hal.kadai07_ih13b_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public static final String DOING_GOOD = "doing_good";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (this.getIntent().getBooleanExtra(ResultActivity.DOING_GOOD, false)) {
            ((ImageView) this.findViewById(R.id.result_image)).setImageDrawable(this.getDrawable(R.drawable.perform_well));
            ((TextView) this.findViewById(R.id.result_text)).setText(this.getText(R.string.perform_well));
        } else {
            ((ImageView) this.findViewById(R.id.result_image)).setImageDrawable(this.getDrawable(R.drawable.perform_unwell));
            ((TextView) this.findViewById(R.id.result_text)).setText(this.getText(R.string.perform_unwell));
        }
    }

    public void continueQuiz(View continueButton) {
        this.finish();
    }
}