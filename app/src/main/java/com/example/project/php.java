package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class php extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar46, bar47, bar48, bar49;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener phpButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.php5_button:
                    web(getString(R.string.php1));
                    break;
                case R.id.php_button:
                    web(getString(R.string.php2));
                    break;
                case R.id.phpoop_button:
                    web(getString(R.string.php3));
                    break;
                case R.id.php2_button:
                    web(getString(R.string.php4));
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
                    case R.id.star_php5:
                        getRate("46", String.valueOf(bar46.getRating()));
                        break;
                    case R.id.star_php:
                        getRate("47", String.valueOf(bar47.getRating()));
                        break;
                    case R.id.star_phpoop:
                        getRate("48", String.valueOf(bar48.getRating()));
                        break;
                    case R.id.star_php2:
                        getRate("49", String.valueOf(bar49.getRating()));
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
        button1 = (Button) findViewById(R.id.php5_button);
        button2 = (Button) findViewById(R.id.php_button);
        button3 = (Button) findViewById(R.id.phpoop_button);
        button4 = (Button) findViewById(R.id.php2_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar46 = (RatingBar) findViewById(R.id.star_php5);
        bar47 = (RatingBar) findViewById(R.id.star_php);
        bar48= (RatingBar) findViewById(R.id.star_phpoop);
        bar49= (RatingBar) findViewById(R.id.star_php2);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(phpButton);
        button2.setOnClickListener(phpButton);
        button3.setOnClickListener(phpButton);
        button4.setOnClickListener(phpButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar46.setOnRatingBarChangeListener(rating);
        bar47.setOnRatingBarChangeListener(rating);
        bar48.setOnRatingBarChangeListener(rating);
        bar49.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar46.setOnRatingBarChangeListener(failRrating);
        bar47.setOnRatingBarChangeListener(failRrating);
        bar48.setOnRatingBarChangeListener(failRrating);
        bar49.setOnRatingBarChangeListener(failRrating);
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
        bar46.setRating(Float.parseFloat(result[45]));
        bar47.setRating(Float.parseFloat(result[46]));
        bar48.setRating(Float.parseFloat(result[47]));
        bar49.setRating(Float.parseFloat(result[48]));
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

