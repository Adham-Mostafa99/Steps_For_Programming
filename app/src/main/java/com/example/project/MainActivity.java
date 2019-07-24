package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView java, cpp, android, csharp, arduino, python, github, kotlin, css, html, js, php;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        varImage();
        setClick();
        checkConnection();
    }

    //when click the page will refresh
    public void refresh(View view) {
        Intent refresh = new Intent();
        refresh.setClass(this, MainActivity.class);
        startActivity(refresh);
    }


    //on click to choose the course
    View.OnClickListener go_to_courses = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.java:
                    page(java.class);
                    break;
                case R.id.cpp:
                    page(cpp.class);
                    break;
                case R.id.android:
                    page(android.class);
                    break;
                case R.id.python:
                    page(python.class);
                    break;
                case R.id.arduino:
                    page(arduino.class);
                    break;
                case R.id.csharp:
                    page(csharp.class);
                    break;
                case R.id.github:
                    page(github.class);
                    break;
                case R.id.kotlin:
                    page(kotlin.class);
                    break;
                case R.id.css:
                    page(css.class);
                    break;
                case R.id.html:
                    page(html.class);
                    break;
                case R.id.js:
                    page(js.class);
                    break;
                case R.id.php:
                    page(php.class);
                    break;
            }
        }
    };

    public void varImage() {
        java = (ImageView) findViewById(R.id.java);
        cpp = (ImageView) findViewById(R.id.cpp);
        android = (ImageView) findViewById(R.id.android);
        csharp = (ImageView) findViewById(R.id.csharp);
        arduino = (ImageView) findViewById(R.id.arduino);
        python = (ImageView) findViewById(R.id.python);
        github = (ImageView) findViewById(R.id.github);
        kotlin = (ImageView) findViewById(R.id.kotlin);
        css = (ImageView) findViewById(R.id.css);
        html = (ImageView) findViewById(R.id.html);
        js = (ImageView) findViewById(R.id.js);
        php = (ImageView) findViewById(R.id.php);
    }

    public void setClick() {
        java.setOnClickListener(go_to_courses);
        cpp.setOnClickListener(go_to_courses);
        android.setOnClickListener(go_to_courses);
        csharp.setOnClickListener(go_to_courses);
        arduino.setOnClickListener(go_to_courses);
        python.setOnClickListener(go_to_courses);
        github.setOnClickListener(go_to_courses);
        kotlin.setOnClickListener(go_to_courses);
        css.setOnClickListener(go_to_courses);
        html.setOnClickListener(go_to_courses);
        js.setOnClickListener(go_to_courses);
        php.setOnClickListener(go_to_courses);
    }

    //open Pages using intent
    public void page(Class cls) {
        Intent pageIntent = new Intent();
        pageIntent.setClass(getApplicationContext(), cls);
        startActivity(pageIntent);
    }

    //getting the rate of all courses
    public void getRate() {
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }

    //get database if internet connection
    public void checkConnection() {
        if (InternetConnection.checkConnection(this)) {
            getRate();
        } else {
            Toast.makeText(this, "no acees", Toast.LENGTH_SHORT).show();
        }
    }
}
