package com.example.android.jokesdisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesDisplayActivity extends AppCompatActivity {
    public static String JOKE_KEY = "Joke key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_display);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY);
        TextView jokeTextView = findViewById(R.id.jokedisplay_tv);
        jokeTextView.setText(joke);
    }
}
