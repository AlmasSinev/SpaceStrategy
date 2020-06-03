package com.plassrever.spacestrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();

        String winner = intent.getStringExtra("winner");
        ImageView imageView;
        if (winner.equals("REDTEAM"))
            imageView = findViewById(R.id.winred_image);
        else
            imageView = findViewById(R.id.winblue_image);

        imageView.setVisibility(View.VISIBLE);
    }
}
