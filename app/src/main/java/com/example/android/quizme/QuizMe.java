package com.example.android.quizme;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizMe extends AppCompatActivity {

    int goodAnswers = 0;

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    TextView question1, question2, question3, question4, question5, question6, question7, question8;
    EditText editText1, editText2;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7;

    int question1answer, question2answer, question5answer, question8answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_me_layout);

        radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radio_group3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radio_group4);

        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        question3 = (TextView) findViewById(R.id.question3);
        question4 = (TextView) findViewById(R.id.question4);
        question5 = (TextView) findViewById(R.id.question5);
        question6 = (TextView) findViewById(R.id.question6);
        question7 = (TextView) findViewById(R.id.question7);
        question8 = (TextView) findViewById(R.id.question8);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkBox7);

        if (savedInstanceState != null) {
            question1answer = savedInstanceState.getInt("question1answer");
            question2answer = savedInstanceState.getInt("question2answer");
            question5answer = savedInstanceState.getInt("question5answer");
            question8answer = savedInstanceState.getInt("question8answer");
            if (radioGroup1.getChildAt(question1answer) != null)
                radioGroup1.check(radioGroup1.getChildAt(question1answer).getId());
            if (radioGroup2.getChildAt(question2answer) != null)
                radioGroup2.check(radioGroup2.getChildAt(question2answer).getId());
            if (radioGroup3.getChildAt(question5answer) != null)
                radioGroup3.check(radioGroup3.getChildAt(question5answer).getId());
            if (radioGroup4.getChildAt(question8answer) != null)
                radioGroup4.check(radioGroup4.getChildAt(question8answer).getId());
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        question1answer = radioGroup1.indexOfChild(findViewById(radioGroup1.getCheckedRadioButtonId()));
        question2answer = radioGroup2.indexOfChild(findViewById(radioGroup2.getCheckedRadioButtonId()));
        question5answer = radioGroup3.indexOfChild(findViewById(radioGroup3.getCheckedRadioButtonId()));
        question8answer = radioGroup4.indexOfChild(findViewById(radioGroup4.getCheckedRadioButtonId()));
        savedInstanceState.putInt("question1answer", question1answer);
        savedInstanceState.putInt("question2answer", question2answer);
        savedInstanceState.putInt("question5answer", question5answer);
        savedInstanceState.putInt("question8answer", question8answer);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void submitAnswers(View view) {
        checkAnswers();
        Intent gameResults = new Intent(this, Results.class);
        gameResults.putExtra("answers", goodAnswers);
        startActivity(gameResults);
    }

    private void checkAnswers() {

        // Question 1
        question1answer = radioGroup1.indexOfChild(findViewById(radioGroup1.getCheckedRadioButtonId()));
        if (question1answer == 0) goodAnswers++;

        // Questin 2
        question2answer = radioGroup2.indexOfChild(findViewById(radioGroup2.getCheckedRadioButtonId()));
        if (question2answer == 0) goodAnswers++;

        // Questin 5
        question5answer = radioGroup3.indexOfChild(findViewById(radioGroup3.getCheckedRadioButtonId()));
        if (question5answer == 0) goodAnswers++;

        // Question 8
        question8answer = radioGroup4.indexOfChild(findViewById(radioGroup4.getCheckedRadioButtonId()));
        if (question8answer == 0) goodAnswers++;

        // Questin 3
        String question3answer = editText1.getText().toString();
        if (question3answer.toLowerCase().equals(getResources().getString(R.string.EditTextAnswer1).toLowerCase()))
            goodAnswers++;

        //Questin 4
        boolean question4aswer1 = checkBox1.isChecked();
        boolean question4aswer2 = checkBox2.isChecked();
        boolean question4aswer3 = checkBox3.isChecked();
        boolean question4aswer4 = checkBox4.isChecked();
        if (question4aswer1 && question4aswer2 && !question4aswer3 && question4aswer4) {
            goodAnswers++;
        }


        // Question 6
        String question6answer = editText2.getText().toString();
        if (question6answer.toLowerCase().equals(getResources().getString(R.string.EditTextAnswer2).toLowerCase()))
            goodAnswers++;


        // Question 7
        boolean question7answer1 = checkBox5.isChecked();
        boolean question7answer2 = checkBox6.isChecked();
        boolean question7answer3 = checkBox7.isChecked();

        if (question7answer1 && question7answer2 && question7answer3) goodAnswers++;
    }
}

