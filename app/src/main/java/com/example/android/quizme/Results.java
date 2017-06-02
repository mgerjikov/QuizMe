package com.example.android.quizme;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        addListenerOnRatingBar();
        addListenerOnButton();

        int score = getIntent().getIntExtra("answers", 0);
        showMessage(score);

    }

    private void showMessage(int score) {
        String messageToShow;

        if (score > 6) {
            messageToShow = getResources().getString(R.string.resultMessage1, score);
        } else if (score > 3) {
            messageToShow = getResources().getString(R.string.resultMessage2, score);
        } else {
            messageToShow = getResources().getString(R.string.resultMessage3, score);
        }

        Spannable centeredMessage = new SpannableString(messageToShow);
        centeredMessage.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0, messageToShow.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        Toast.makeText(this, centeredMessage, Toast.LENGTH_LONG).show();
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(Results.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
                quizOver();
            }

        });
    }

    private void quizOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Results.this);
        alertDialogBuilder
                .setMessage("Thank's for rating us! We Hope you enjoyed it! ")
                .setCancelable(false)
                .setPositiveButton("Try Again",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        })
                .setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

}
