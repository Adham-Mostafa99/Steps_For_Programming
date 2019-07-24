package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class github extends AppCompatActivity {
    Button button1, button2, button3;
    RatingBar bar26, bar27, bar28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        varButtons();
        setButton();
        varRatingBar();
        setfailRatingBar();
        checkConnection();
        setRatingBar();
    }

    View.OnClickListener gitButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.github_button:
                    web(getString(R.string.git1));
                    break;
                case R.id.github2_button:
                    web(getString(R.string.git2));
                    break;
                case R.id.github3_button:
                    web(getString(R.string.git3));
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
                    case R.id.star_gitgub:
                        getRate("26", String.valueOf(bar26.getRating()));
                        break;
                    case R.id.star_gitgub2:
                        getRate("27", String.valueOf(bar27.getRating()));
                        break;
                    case R.id.star_gitgub3:
                        getRate("28", String.valueOf(bar28.getRating()));
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
        button1 = (Button) findViewById(R.id.github_button);
        button2 = (Button) findViewById(R.id.github2_button);
        button3 = (Button) findViewById(R.id.github3_button);

    }

    //decleration ratingBar
    public void varRatingBar() {
        bar26 = (RatingBar) findViewById(R.id.star_gitgub);
        bar27 = (RatingBar) findViewById(R.id.star_gitgub2);
        bar28 = (RatingBar) findViewById(R.id.star_gitgub3);
    }

    //onclick for buttons
    public void setButton() {
        button1.setOnClickListener(gitButton);
        button2.setOnClickListener(gitButton);
        button3.setOnClickListener(gitButton);

    }

    //onclick for RatingBar
    public void setRatingBar() {
        bar26.setOnRatingBarChangeListener(rating);
        bar27.setOnRatingBarChangeListener(rating);
        bar28.setOnRatingBarChangeListener(rating);
    }

    public void setfailRatingBar() {
        bar26.setOnRatingBarChangeListener(failRrating);
        bar27.setOnRatingBarChangeListener(failRrating);
        bar28.setOnRatingBarChangeListener(failRrating);
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
        bar26.setRating(Float.parseFloat(result[25]));
        bar27.setRating(Float.parseFloat(result[26]));
        bar28.setRating(Float.parseFloat(result[27]));
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
