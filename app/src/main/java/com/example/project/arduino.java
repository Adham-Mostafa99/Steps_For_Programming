package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class arduino extends AppCompatActivity {
    Button button1, button2, button3;
    RatingBar bar22, bar23, bar24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener arduinoButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.basic_arduino_button:
                    web(getString(R.string.arduino1));
                    break;
                case R.id.arduino1_button:
                    web(getString(R.string.arduino2));
                    break;
                case R.id.arduino2_button:
                    web(getString(R.string.arduino3));
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
                    case R.id.star_basic_arduino:
                        getRate("22", String.valueOf(bar22.getRating()));
                        break;
                    case R.id.star_arduino1:
                        getRate("23", String.valueOf(bar23.getRating()));
                        break;
                    case R.id.star_arduino2:
                        getRate("24", String.valueOf(bar24.getRating()));
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
        button1 = (Button) findViewById(R.id.basic_arduino_button);
        button2 = (Button) findViewById(R.id.arduino1_button);
        button3 = (Button) findViewById(R.id.arduino2_button);

    }

    //decleration ratingBar
    public void varRatingBar() {
        bar22 = (RatingBar) findViewById(R.id.star_basic_arduino);
        bar23 = (RatingBar) findViewById(R.id.star_arduino1);
        bar24 = (RatingBar) findViewById(R.id.star_arduino2);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(arduinoButton);
        button2.setOnClickListener(arduinoButton);
        button3.setOnClickListener(arduinoButton);

    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar22.setOnRatingBarChangeListener(rating);
        bar23.setOnRatingBarChangeListener(rating);
        bar24.setOnRatingBarChangeListener(rating);
    }

    //onclick for RatingBar
    public void setfailRatingBar() {
        bar22.setOnRatingBarChangeListener(failRrating);
        bar23.setOnRatingBarChangeListener(failRrating);
        bar24.setOnRatingBarChangeListener(failRrating);
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
        bar22.setRating(Float.parseFloat(result[21]));
        bar23.setRating(Float.parseFloat(result[22]));
        bar24.setRating(Float.parseFloat(result[23]));
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
