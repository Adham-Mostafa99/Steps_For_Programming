package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class js extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar42, bar43, bar44, bar45;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener jsButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.js_button:
                    web(getString(R.string.js1));
                    break;
                case R.id.js2_button:
                    web(getString(R.string.js2));
                    break;
                case R.id.js3_button:
                    web(getString(R.string.js3));
                    break;
                case R.id.js4_button:
                    web(getString(R.string.js4));
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
                    case R.id.star_js:
                        getRate("42", String.valueOf(bar42.getRating()));
                        break;
                    case R.id.star_js2:
                        getRate("43", String.valueOf(bar43.getRating()));
                        break;
                    case R.id.star_js3:
                        getRate("44", String.valueOf(bar44.getRating()));
                        break;
                    case R.id.star_js4:
                        getRate("45", String.valueOf(bar45.getRating()));
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
        button1 = (Button) findViewById(R.id.js_button);
        button2 = (Button) findViewById(R.id.js2_button);
        button3 = (Button) findViewById(R.id.js3_button);
        button4 = (Button) findViewById(R.id.js4_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar42 = (RatingBar) findViewById(R.id.star_js);
        bar43 = (RatingBar) findViewById(R.id.star_js2);
        bar44 = (RatingBar) findViewById(R.id.star_js3);
        bar45 = (RatingBar) findViewById(R.id.star_js4);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(jsButton);
        button2.setOnClickListener(jsButton);
        button3.setOnClickListener(jsButton);
        button4.setOnClickListener(jsButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar42.setOnRatingBarChangeListener(rating);
        bar43.setOnRatingBarChangeListener(rating);
        bar44.setOnRatingBarChangeListener(rating);
        bar45.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar42.setOnRatingBarChangeListener(failRrating);
        bar43.setOnRatingBarChangeListener(failRrating);
        bar44.setOnRatingBarChangeListener(failRrating);
        bar45.setOnRatingBarChangeListener(failRrating);
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
        bar42.setRating(Float.parseFloat(result[41]));
        bar43.setRating(Float.parseFloat(result[42]));
        bar44.setRating(Float.parseFloat(result[43]));
        bar45.setRating(Float.parseFloat(result[44]));
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
