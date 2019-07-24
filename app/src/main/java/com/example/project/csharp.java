package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class csharp extends AppCompatActivity {
    Button button1, button2, button3;
    RatingBar bar11, bar12, bar13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csharp);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener cSahrpButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.csharp_button:
                    web(getString(R.string.csharp1));
                    break;
                case R.id.oopcsharp_button:
                    web(getString(R.string.csharp2));
                    break;
                case R.id.fullcsharp_button:
                    web(getString(R.string.csharp3));
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
                    case R.id.star_csharp:
                        getRate("1", String.valueOf(bar11.getRating()));
                        break;
                    case R.id.star_oopcsharp:
                        getRate("2", String.valueOf(bar12.getRating()));
                        break;
                    case R.id.star_fullcsharp:
                        getRate("3", String.valueOf(bar13.getRating()));
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
        button1 = (Button) findViewById(R.id.csharp_button);
        button2 = (Button) findViewById(R.id.oopcsharp_button);
        button3 = (Button) findViewById(R.id.fullcsharp_button);

    }

    //decleration ratingBar
    public void varRatingBar() {
        bar11 = (RatingBar) findViewById(R.id.star_csharp);
        bar12 = (RatingBar) findViewById(R.id.star_oopcsharp);
        bar13 = (RatingBar) findViewById(R.id.star_fullcsharp);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(cSahrpButton);
        button2.setOnClickListener(cSahrpButton);
        button3.setOnClickListener(cSahrpButton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar11.setOnRatingBarChangeListener(rating);
        bar12.setOnRatingBarChangeListener(rating);
        bar13.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar11.setOnRatingBarChangeListener(failRrating);
        bar12.setOnRatingBarChangeListener(failRrating);
        bar13.setOnRatingBarChangeListener(failRrating);
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
        bar11.setRating(Float.parseFloat(result[10]));
        bar12.setRating(Float.parseFloat(result[11]));
        bar13.setRating(Float.parseFloat(result[12]));
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
