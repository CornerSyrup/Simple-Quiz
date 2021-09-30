package jp.ac.hal.kadai07_ih13b_11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void startQuiz(View startButton) {
        this.startActivity(new Intent(this, QuizActivity.class));
    }
}