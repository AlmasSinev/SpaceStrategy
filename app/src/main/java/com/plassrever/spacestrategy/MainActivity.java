package com.plassrever.spacestrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView redTeamCoinsText, blueTeamCoinsText;
    private GridView redTeamGrid, blueTeamGrid, storeGrid;

    Dialog storeDialog;

    public GameLoop gameLoop = new GameLoop();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeDialog = new Dialog(this);

        redTeamCoinsText = findViewById(R.id.redteam_coins_text);
        blueTeamCoinsText = findViewById(R.id.blueteam_coins_text);

        gameLoop.addCoinsOfTime(redTeamCoinsText, blueTeamCoinsText);

        redTeamGrid = findViewById(R.id.redteam_grid);
        blueTeamGrid = findViewById(R.id.blueteam_grid);

        redTeamGrid.setAdapter(new ImageAdapter(this));
        blueTeamGrid.setAdapter(new ImageAdapter(this));

        redTeamGrid.setOnItemClickListener(redTeamGridOnClickListener);
        blueTeamGrid.setOnItemClickListener(blueTeamGridOnClickListener);
    }

    private GridView.OnItemClickListener redTeamGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            createStoreDialog();
        }
    };

    private GridView.OnItemClickListener blueTeamGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            createStoreDialog();
        }
    };

    private GridView.OnItemClickListener storeGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();
        }
    };

    private void createStoreDialog() {
        storeDialog.setContentView(R.layout.store_dialog);
        storeGrid = storeDialog.findViewById(R.id.grid_dialog2);
        storeGrid.setAdapter(new StoreAdapter(this));
        storeGrid.setOnItemClickListener(storeGridOnClickListener);
        storeDialog.show();
    }
}
