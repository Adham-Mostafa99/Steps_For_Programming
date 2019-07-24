package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class css extends AppCompatActivity {
    Button button1, button2, button3, button4, button5;
    RatingBar bar33, bar34, bar35, bar36, bar37;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener cssButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.css_button:
                    web(getString(R.string.css1));
                    break;
                case R.id.css2_button:
                    web(getString(R.string.css2));
                    break;
                case R.id.css3_button:
                    web(getString(R.string.css3));
                    break;
                case R.id.css3_1_button:
                    web(getString(R.string.css4));
                    break;
                case R.id.css3_2_button:
                    web(getString(R.string.css5));
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
                    case R.id.star_css:
                        getRate("33", String.valueOf(bar33.getRating()));
                        break;
                    case R.id.star_css2:
                        getRate("34", String.valueOf(bar34.getRating()));
                        break;
                    case R.id.star_css3:
                        getRate("35", String.valueOf(bar35.getRating()));
                        break;
                    case R.id.star_css3_1:
                        getRate("36", String.valueOf(bar36.getRating()));
                        break;
                    case R.id.star_css3_2:
                        getRate("37", String.valueOf(bar37.getRating()));
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
        button1 = (Button) findViewById(R.id.css_button);
        button2 = (Button) findViewById(R.id.css2_button);
        button3 = (Button) findViewById(R.id.css3_button);
        button4 = (Button) findViewById(R.id.css3_1_button);
        button5 = (Button) findViewById(R.id.css3_2_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar33 = (RatingBar) findViewById(R.id.star_css);
        bar34 = (RatingBar) findViewById(R.id.star_css2);
        bar35 = (RatingBar) findViewById(R.id.star_css3);
        bar36 = (RatingBar) findViewById(R.id.star_css3_1);
        bar37 = (RatingBar) findViewById(R.id.star_css3_2);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(cssButton);
        button2.setOnClickListener(cssButton);
        button3.setOnClickListener(cssButton);
        button4.setOnClickListener(cssButton);
        button5.setOnClickListener(cssButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar33.setOnRatingBarChangeListener(rating);
        bar34.setOnRatingBarChangeListener(rating);
        bar35.setOnRatingBarChangeListener(rating);
        bar36.setOnRatingBarChangeListener(rating);
        bar37.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar33.setOnRatingBarChangeListener(failRrating);
        bar34.setOnRatingBarChangeListener(failRrating);
        bar35.setOnRatingBarChangeListener(failRrating);
        bar36.setOnRatingBarChangeListener(failRrating);
        bar37.setOnRatingBarChangeListener(failRrating);
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
        bar33.setRating(Float.parseFloat(result[32]));
        bar34.setRating(Float.parseFloat(result[33]));
        bar35.setRating(Float.parseFloat(result[34]));
        bar36.setRating(Float.parseFloat(result[35]));
        bar37.setRating(Float.parseFloat(result[36]));
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

