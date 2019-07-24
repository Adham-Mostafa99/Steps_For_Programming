package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class android extends AppCompatActivity {
    Button button1, button2, button3, button4;
    RatingBar bar14, bar15, bar16, bar17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        varButtons();
        setButton();
        varRatingBar();
        setFailRatingBar();
        checkConnection();
        setRatingBar();

    }

    View.OnClickListener androidButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.android1_button:
                    web(getString(R.string.android1));
                    break;
                case R.id.android2_button:
                    web(getString(R.string.android2));
                    break;
                case R.id.android3_button:
                    web(getString(R.string.android3));
                    break;
                case R.id.android4_button:
                    web(getString(R.string.android4));
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
                    case R.id.star_android1:
                        getRate("14", String.valueOf(bar14.getRating()));
                        break;
                    case R.id.star_android2:
                        getRate("15", String.valueOf(bar15.getRating()));
                        break;
                    case R.id.star_android3:
                        getRate("16", String.valueOf(bar16.getRating()));
                        break;
                    case R.id.star_android4:
                        getRate("17", String.valueOf(bar17.getRating()));
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
        button1 = (Button) findViewById(R.id.android1_button);
        button2 = (Button) findViewById(R.id.android2_button);
        button3 = (Button) findViewById(R.id.android3_button);
        button4 = (Button) findViewById(R.id.android4_button);

    }

    //decleration ratingBar
    public void varRatingBar() {
        bar14 = (RatingBar) findViewById(R.id.star_android1);
        bar15 = (RatingBar) findViewById(R.id.star_android2);
        bar16 = (RatingBar) findViewById(R.id.star_android3);
        bar17 = (RatingBar) findViewById(R.id.star_android4);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(androidButton);
        button2.setOnClickListener(androidButton);
        button3.setOnClickListener(androidButton);
        button4.setOnClickListener(androidButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar14.setOnRatingBarChangeListener(rating);
        bar15.setOnRatingBarChangeListener(rating);
        bar16.setOnRatingBarChangeListener(rating);
        bar17.setOnRatingBarChangeListener(rating);
    }

    public void setFailRatingBar() {
        bar14.setOnRatingBarChangeListener(failRrating);
        bar15.setOnRatingBarChangeListener(failRrating);
        bar16.setOnRatingBarChangeListener(failRrating);
        bar17.setOnRatingBarChangeListener(failRrating);
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

    public void setRating(String[] result) {
        bar14.setRating(Float.parseFloat(result[13]));
        bar15.setRating(Float.parseFloat(result[14]));
        bar16.setRating(Float.parseFloat(result[15]));
        bar17.setRating(Float.parseFloat(result[16]));
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
