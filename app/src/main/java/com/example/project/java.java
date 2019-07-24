package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class java extends AppCompatActivity {
    Button java101, java102, java103, java104;
    RatingBar bar1, bar2, bar3, bar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        varButtons();
        setButton();
        varRatingBar();
        setFailRatingBar();
        checkConnection();
        setRatingBar();
    }

    //onclick for button
    View.OnClickListener javabutton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.java101_button:
                    web(getString(R.string.java101));
                    break;
                case R.id.java102_button:
                    web(getString(R.string.java102));
                    break;
                case R.id.java103_button:
                    web(getString(R.string.java103));
                    break;
                case R.id.java104_button:
                    web(getString(R.string.java104));
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
                    case R.id.star_java101:
                        getRate("1", String.valueOf(bar1.getRating()));
                        break;
                    case R.id.star_java102:
                        getRate("2", String.valueOf(bar2.getRating()));
                        break;
                    case R.id.star_java103:
                        getRate("3", String.valueOf(bar3.getRating()));
                        break;
                    case R.id.star_java104:
                        getRate("4", String.valueOf(bar4.getRating()));
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
        java101 = (Button) findViewById(R.id.java101_button);
        java102 = (Button) findViewById(R.id.java102_button);
        java103 = (Button) findViewById(R.id.java103_button);
        java104 = (Button) findViewById(R.id.java104_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar1 = (RatingBar) findViewById(R.id.star_java101);
        bar2 = (RatingBar) findViewById(R.id.star_java102);
        bar3 = (RatingBar) findViewById(R.id.star_java103);
        bar4 = (RatingBar) findViewById(R.id.star_java104);
    }

    //onclick for buttons
    public void setButton() {
        java101.setOnClickListener(javabutton);
        java102.setOnClickListener(javabutton);
        java103.setOnClickListener(javabutton);
        java104.setOnClickListener(javabutton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar1.setOnRatingBarChangeListener(rating);
        bar2.setOnRatingBarChangeListener(rating);
        bar3.setOnRatingBarChangeListener(rating);
        bar4.setOnRatingBarChangeListener(rating);
    }

    public void setFailRatingBar() {
        bar1.setOnRatingBarChangeListener(failRrating);
        bar2.setOnRatingBarChangeListener(failRrating);
        bar3.setOnRatingBarChangeListener(failRrating);
        bar4.setOnRatingBarChangeListener(failRrating);
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
        bar1.setRating(Float.parseFloat(result[0]));
        bar2.setRating(Float.parseFloat(result[1]));
        bar3.setRating(Float.parseFloat(result[2]));
        bar4.setRating(Float.parseFloat(result[3]));
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
