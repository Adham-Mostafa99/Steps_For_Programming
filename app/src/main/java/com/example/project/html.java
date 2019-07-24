package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class html extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar38, bar39, bar40, bar41;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();

    }

    View.OnClickListener htmlButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.html_button:
                    web(getString(R.string.html1));
                    break;
                case R.id.html5_button:
                    web(getString(R.string.html2));
                    break;
                case R.id.html2_button:
                    web(getString(R.string.html3));
                    break;
                case R.id.html5_2_button:
                    web(getString(R.string.html4));
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
                    case R.id.star_html:
                        getRate("38", String.valueOf(bar38.getRating()));
                        break;
                    case R.id.star_html5:
                        getRate("39", String.valueOf(bar39.getRating()));
                        break;
                    case R.id.star_html2:
                        getRate("40", String.valueOf(bar40.getRating()));
                        break;
                    case R.id.star_html5_2:
                        getRate("41", String.valueOf(bar41.getRating()));
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
        button1 = (Button) findViewById(R.id.html_button);
        button2 = (Button) findViewById(R.id.html5_button);
        button3 = (Button) findViewById(R.id.html2_button);
        button4 = (Button) findViewById(R.id.html5_2_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar38 = (RatingBar) findViewById(R.id.star_html);
        bar39 = (RatingBar) findViewById(R.id.star_html5);
        bar40 = (RatingBar) findViewById(R.id.star_html2);
        bar41 = (RatingBar) findViewById(R.id.star_html5_2);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(htmlButton);
        button2.setOnClickListener(htmlButton);
        button3.setOnClickListener(htmlButton);
        button4.setOnClickListener(htmlButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar38.setOnRatingBarChangeListener(rating);
        bar39.setOnRatingBarChangeListener(rating);
        bar40.setOnRatingBarChangeListener(rating);
        bar41.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar38.setOnRatingBarChangeListener(failRrating);
        bar39.setOnRatingBarChangeListener(failRrating);
        bar40.setOnRatingBarChangeListener(failRrating);
        bar41.setOnRatingBarChangeListener(failRrating);
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
        bar38.setRating(Float.parseFloat(result[37]));
        bar39.setRating(Float.parseFloat(result[38]));
        bar40.setRating(Float.parseFloat(result[39]));
        bar41.setRating(Float.parseFloat(result[40]));
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
