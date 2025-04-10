package com.example.quizapp__224385035;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private Button restartButton;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // To get scores
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        userName = intent.getStringExtra("USER_NAME");

        scoreTextView = findViewById(R.id.scoreTextView);
        restartButton = findViewById(R.id.restartButton);

        // To display scores information
        String scoreText = "Score: " + score + "/5\n\nCongratulation to your submission!";
        scoreTextView.setText(scoreText);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
                finish();
            }
        });
    }
} 