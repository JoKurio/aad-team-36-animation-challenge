package io.github.jokurio.aqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        String comment= "";
        AndroidQuiz quiz = new AndroidQuiz();
        TextView scoreTV = findViewById(R.id.scoreTxtView);
        scoreTV.setText(String.valueOf(quiz.finalScore));

        if (quiz.finalScore == 30) {
            comment = "Perfect! You got all 6 questions right";
        } else if (quiz.finalScore == 25) {
            comment = "Very good! You answered 5 questions correctly";
        } else if (quiz.finalScore == 20) {
            scoreTV.setTextColor(Color.YELLOW);
            comment = "Good! You got 4 questions right";
        } else if (quiz.finalScore == 15) {
            scoreTV.setTextColor(Color.YELLOW);
            comment = "Okay, you got 3 questions right";
        } else if (quiz.finalScore == 10) {
            scoreTV.setTextColor(Color.RED);
            comment = "Just 2 out of 6, you can do better.";
        } else if (quiz.finalScore == 5) {
            scoreTV.setTextColor(Color.RED);
            comment = "5 points out of 30. Not too good.";
        } else if (quiz.finalScore == 0) {
            scoreTV.setTextColor(Color.RED);
            comment = "Bad day, or Android is just not your thing?";
        }
        TextView commentTV = findViewById(R.id.commentTxtView);
        commentTV.setText(comment);

        findViewById(R.id.imageButton).setOnClickListener(
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ScoreActivity.this, AndroidQuiz.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                })
        );
    }
}
