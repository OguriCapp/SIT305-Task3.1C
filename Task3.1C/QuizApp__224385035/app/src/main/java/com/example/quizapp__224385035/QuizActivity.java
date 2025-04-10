package com.example.quizapp__224385035;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1, option2, option3;
    private Button submitButton;
    private ProgressBar progressBar;
    private TextView progressText;
    private String userName;
    private int currentQuestion = 0;
    private int score = 0;
    private boolean isAnswerChecked = false;

    private String[] questions = {
            "Is Deakin University located in Australia?",
            "44+56=?",
            "20*21=?",
            "80-98=?",
            "2%5=?"
    };

    private String[][] options = {
            {"Yes", "No", "There is no Deakin University in the world"},
            {"110", "100", "90"},
            {"4200", "410", "420"},
            {"-12", "-18", "12"},
            {"2", "5", "0"}
    };

    private int[] correctAnswers = {0, 1, 2, 1, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("USER_NAME");

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        progressBar.setMax(questions.length);
        showQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerChecked) {
                    checkAnswer();
                } else {
                    moveToNextQuestion();
                }
            }
        });
    }

    private void showQuestion() {
        if (currentQuestion < questions.length) {
            questionTextView.setText(questions[currentQuestion]);
            option1.setText(options[currentQuestion][0]);
            option2.setText(options[currentQuestion][1]);
            option3.setText(options[currentQuestion][2]);
            
            // Progress Bar
            int progress = currentQuestion + 1;
            progressBar.setProgress(progress);
            int percentage = (progress * 100) / questions.length;
            progressText.setText(percentage + "%");
            
            isAnswerChecked = false;
            submitButton.setText("Submit");
            resetRadioButtons();
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("USER_NAME", userName);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            int selectedAnswer = optionsRadioGroup.indexOfChild(selectedRadioButton);

            RadioButton correctRadioButton = (RadioButton) optionsRadioGroup.getChildAt(correctAnswers[currentQuestion]);

            correctRadioButton.setBackgroundColor(Color.GREEN);
            
            if (selectedAnswer != correctAnswers[currentQuestion]) {
                selectedRadioButton.setBackgroundColor(Color.RED);
            }

            if (selectedAnswer == correctAnswers[currentQuestion]) {
                score++;
            }

            optionsRadioGroup.setEnabled(false);
            isAnswerChecked = true;
            submitButton.setText("Next");
        }
    }

    private void moveToNextQuestion() {
        currentQuestion++;
        showQuestion();
    }

    private void resetRadioButtons() {
        optionsRadioGroup.clearCheck();
        optionsRadioGroup.setEnabled(true);
        option1.setBackgroundColor(Color.TRANSPARENT);
        option2.setBackgroundColor(Color.TRANSPARENT);
        option3.setBackgroundColor(Color.TRANSPARENT);
    }
} 