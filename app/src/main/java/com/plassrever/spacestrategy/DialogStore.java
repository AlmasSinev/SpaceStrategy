package com.plassrever.spacestrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class DialogStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_store);

        TextView text = findViewById(R.id.coins_dialog);
        text.setText("1000");
        GridView storeGrid = findViewById(R.id.grid_dialog);
        storeGrid.setAdapter(new StoreAdapter(this));
    }

}
