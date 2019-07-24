package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class cpp extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6;
    RatingBar bar5, bar6, bar7, bar8, bar9, bar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    //onclick for button
    View.OnClickListener cppbutton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.basic_cpp_button:
                    web(getString(R.string.basicCpp));
                    break;
                case R.id.basic2_cpp_button:
                    web(getString(R.string.basic2Cpp));
                    break;
                case R.id.oop_cpp_button:
                    web(getString(R.string.oopCpp));
                    break;
                case R.id.oop2_cpp_button:
                    web(getString(R.string.oop2Cpp));
                    break;
                case R.id.DS_cpp_button:
                    web(getString(R.string.dsCpp));
                    break;
                case R.id.DS2_cpp_button:
                    web(getString(R.string.ds2Cpp));
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
                    case R.id.star_basic_cpp:
                        getRate("1", String.valueOf(bar5.getRating()));
                        break;
                    case R.id.star_basic2_cpp:
                        getRate("2", String.valueOf(bar6.getRating()));
                        break;
                    case R.id.star_oop_cpp:
                        getRate("3", String.valueOf(bar7.getRating()));
                        break;
                    case R.id.star_oop2_cpp:
                        getRate("4", String.valueOf(bar8.getRating()));
                        break;
                    case R.id.star_DS_cpp:
                        getRate("4", String.valueOf(bar9.getRating()));
                        break;
                    case R.id.star_DS2_cpp:
                        getRate("4", String.valueOf(bar10.getRating()));
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
        button1 = (Button) findViewById(R.id.basic_cpp_button);
        button2 = (Button) findViewById(R.id.basic2_cpp_button);
        button3 = (Button) findViewById(R.id.oop_cpp_button);
        button4 = (Button) findViewById(R.id.oop2_cpp_button);
        button5 = (Button) findViewById(R.id.DS_cpp_button);
        button6 = (Button) findViewById(R.id.DS2_cpp_button);
    }

    //decleration ratingBar
    public void varRatingBar() {
        bar5 = (RatingBar) findViewById(R.id.star_basic_cpp);
        bar6 = (RatingBar) findViewById(R.id.star_basic2_cpp);
        bar7 = (RatingBar) findViewById(R.id.star_oop_cpp);
        bar8 = (RatingBar) findViewById(R.id.star_oop2_cpp);
        bar9 = (RatingBar) findViewById(R.id.star_DS_cpp);
        bar10 = (RatingBar) findViewById(R.id.star_DS2_cpp);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(cppbutton);
        button2.setOnClickListener(cppbutton);
        button3.setOnClickListener(cppbutton);
        button4.setOnClickListener(cppbutton);
        button5.setOnClickListener(cppbutton);
        button6.setOnClickListener(cppbutton);
    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar5.setOnRatingBarChangeListener(rating);
        bar6.setOnRatingBarChangeListener(rating);
        bar7.setOnRatingBarChangeListener(rating);
        bar8.setOnRatingBarChangeListener(rating);
        bar9.setOnRatingBarChangeListener(rating);
        bar10.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar5.setOnRatingBarChangeListener(failRrating);
        bar6.setOnRatingBarChangeListener(failRrating);
        bar7.setOnRatingBarChangeListener(failRrating);
        bar8.setOnRatingBarChangeListener(failRrating);
        bar9.setOnRatingBarChangeListener(failRrating);
        bar10.setOnRatingBarChangeListener(failRrating);
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
        bar5.setRating(Float.parseFloat(result[4]));
        bar6.setRating(Float.parseFloat(result[5]));
        bar7.setRating(Float.parseFloat(result[6]));
        bar8.setRating(Float.parseFloat(result[7]));
        bar9.setRating(Float.parseFloat(result[8]));
        bar10.setRating(Float.parseFloat(result[9]));
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
