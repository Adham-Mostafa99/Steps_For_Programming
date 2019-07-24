package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class kotlin extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar29, bar30, bar31, bar32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();

    }

    View.OnClickListener kotlinButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.kotlin_button:
                    web(getString(R.string.kotlin1));
                    break;
                case R.id.kotlin2_button:
                    web(getString(R.string.kotlin2));
                    break;
                case R.id.kotlin3_button:
                    web(getString(R.string.kotlin3));
                    break;
                case R.id.kotlin4_button:
                    web(getString(R.string.kotlin4));
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
                    case R.id.star_kotlin:
                        getRate("29", String.valueOf(bar29.getRating()));
                        break;
                    case R.id.star_kotlin2:
                        getRate("30", String.valueOf(bar30.getRating()));
                        break;
                    case R.id.star_kotlin3:
                        getRate("31", String.valueOf(bar31.getRating()));
                        break;
                    case R.id.star_kotlin4:
                        getRate("32", String.valueOf(bar32.getRating()));
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
        button1 = (Button) findViewById(R.id.kotlin_button);
        button2 = (Button) findViewById(R.id.kotlin2_button);
        button3 = (Button) findViewById(R.id.kotlin3_button);
        button4 = (Button) findViewById(R.id.kotlin4_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar29 = (RatingBar) findViewById(R.id.star_kotlin);
        bar30 = (RatingBar) findViewById(R.id.star_kotlin2);
        bar31 = (RatingBar) findViewById(R.id.star_kotlin3);
        bar32 = (RatingBar) findViewById(R.id.star_kotlin4);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(kotlinButton);
        button2.setOnClickListener(kotlinButton);
        button3.setOnClickListener(kotlinButton);
        button4.setOnClickListener(kotlinButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar29.setOnRatingBarChangeListener(rating);
        bar30.setOnRatingBarChangeListener(rating);
        bar31.setOnRatingBarChangeListener(rating);
        bar32.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar29.setOnRatingBarChangeListener(failRrating);
        bar30.setOnRatingBarChangeListener(failRrating);
        bar31.setOnRatingBarChangeListener(failRrating);
        bar32.setOnRatingBarChangeListener(failRrating);
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
        bar29.setRating(Float.parseFloat(result[28]));
        bar30.setRating(Float.parseFloat(result[29]));
        bar31.setRating(Float.parseFloat(result[30]));
        bar32.setRating(Float.parseFloat(result[31]));
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
