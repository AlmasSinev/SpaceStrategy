package com.plassrever.spacestrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView redTeamCoinsText, blueTeamCoinsText;
    private GridView redTeamGrid, blueTeamGrid, storeGrid;

    Dialog storeDialog;

    public GameLoop gameLoop = new GameLoop();

    ImageAdapter redAdapter, blueAdapter;
    StoreItemsAdapter storeAdapter;

    private int selectedPosition;
    private boolean blueSelected = false;

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

        redAdapter = new ImageAdapter(this);
        blueAdapter = new ImageAdapter(this);
        storeAdapter = new StoreItemsAdapter(this);

        redTeamGrid.setAdapter(redAdapter);
        blueTeamGrid.setAdapter(blueAdapter);

        redTeamGrid.setOnItemClickListener(redTeamGridOnClickListener);
        blueTeamGrid.setOnItemClickListener(blueTeamGridOnClickListener);
    }

    private GridView.OnItemClickListener redTeamGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedPosition = position;
            blueSelected = false;
            storeAdapter.setTeam(blueSelected);
            createStoreDialog();
        }
    };

    private GridView.OnItemClickListener blueTeamGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedPosition = position;
            blueSelected = true;
            storeAdapter.setTeam(blueSelected);
            createStoreDialog();
        }
    };

    private GridView.OnItemClickListener storeGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (blueSelected)
                blueAdapter.setNewValue(selectedPosition, storeAdapter.blueStore[position]);
            else
                redAdapter.setNewValue(selectedPosition, storeAdapter.redStore[position]);
            redTeamGrid.setAdapter(redAdapter);
            blueTeamGrid.setAdapter(blueAdapter);
            storeDialog.cancel();
        }
    };

    private void createStoreDialog() {
        storeDialog.setContentView(R.layout.store_dialog);
        storeGrid = storeDialog.findViewById(R.id.grid_dialog2);
        storeGrid.setAdapter(storeAdapter);
        storeGrid.setOnItemClickListener(storeGridOnClickListener);
        storeDialog.show();
    }
}
