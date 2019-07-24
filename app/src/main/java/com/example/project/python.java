package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class python extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar18, bar19, bar20, bar21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener pythonButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.python1_button:
                    web(getString(R.string.python1));
                    break;
                case R.id.basic_python1_button:
                    web(getString(R.string.python2));
                    break;
                case R.id.basic_python2_button:
                    web(getString(R.string.python3));
                    break;
                case R.id.python2_button:
                    web(getString(R.string.python4));
                    break;
            }

        }
    };

    //onClick (onRating) for RatingBar
    RatingBar.OnRatingBarChangeListener rating = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            if (InternetConnection.checkConnection(getApplicationContext())) {
                switch (ratingBar.getId()) {
                    case R.id.star_python1:
                        getRate("1", String.valueOf(bar18.getRating()));
                        break;
                    case R.id.star_basic1_python:
                        getRate("2", String.valueOf(bar19.getRating()));
                        break;
                    case R.id.star_basic2_python:
                        getRate("3", String.valueOf(bar20.getRating()));
                        break;
                    case R.id.basic2_python:
                        getRate("4", String.valueOf(bar21.getRating()));
                        break;
                }
            } else {
                Toast.makeText(getApplicationContext(), "no acees", Toast.LENGTH_SHORT).show();
            }
        }
    };

    RatingBar.OnRatingBarChangeListener failRrating = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        }

    };

    //decleration buttons
    public void varButtons() {
        button1 = (Button) findViewById(R.id.python1_button);
        button2 = (Button) findViewById(R.id.basic_python1_button);
        button3 = (Button) findViewById(R.id.basic_python2_button);
        button4 = (Button) findViewById(R.id.python2_button);

    }

    //decleration ratingBar
    public void varRatingBar() {
        bar18 = (RatingBar) findViewById(R.id.star_python1);
        bar19 = (RatingBar) findViewById(R.id.star_basic1_python);
        bar20 = (RatingBar) findViewById(R.id.star_basic2_python);
        bar21 = (RatingBar) findViewById(R.id.basic2_python);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(pythonButton);
        button2.setOnClickListener(pythonButton);
        button3.setOnClickListener(pythonButton);
        button4.setOnClickListener(pythonButton);

    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar18.setOnRatingBarChangeListener(rating);
        bar19.setOnRatingBarChangeListener(rating);
        bar20.setOnRatingBarChangeListener(rating);
        bar21.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar18.setOnRatingBarChangeListener(failRrating);
        bar19.setOnRatingBarChangeListener(failRrating);
        bar20.setOnRatingBarChangeListener(failRrating);
        bar21.setOnRatingBarChangeListener(failRrating);
    }

    //open webSite using intent
    public void web(String url) {
        Intent webIntent = new Intent();
        webIntent.setAction(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        } else
            Toast.makeText(this, "No Browser Found !", Toast.LENGTH_SHORT).show();
    }

    //set the rating of each element from mysql
    public void setRating(String[] result) {
        bar18.setRating(Float.parseFloat(result[17]));
        bar19.setRating(Float.parseFloat(result[18]));
        bar20.setRating(Float.parseFloat(result[19]));
        bar21.setRating(Float.parseFloat(result[20]));
    }

    //get the rate from each element and sent it to mysql
    public void getRate(String rate_name, String rate_value) {
        String type = "login";
        BackgroundWorkerSet backgroundWorkerSet = new BackgroundWorkerSet(this);
        backgroundWorkerSet.execute(type, rate_name, rate_value);
    }

    public void checkConnection() {

        if (InternetConnection.checkConnection(this)) {
            setRating(BackgroundWorker.new_result);
        } else {
            Toast.makeText(this, "no acees", Toast.LENGTH_SHORT).show();
        }
    }
}
