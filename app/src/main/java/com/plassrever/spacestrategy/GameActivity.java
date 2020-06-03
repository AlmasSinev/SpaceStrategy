package com.plassrever.spacestrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView redTeamCoinsText, blueTeamCoinsText, redTeamLife, blueTeamLife;
    private GridView redTeamGrid, blueTeamGrid, storeGrid;
    private Button pauseButton;

    Dialog storeDialog, pauseDialog;

    public GameLoop gameLoop = new GameLoop();

    public static ImageAdapter redAdapter, blueAdapter;
    StoreItemsAdapter storeAdapter;

    private int selectedPosition;
    private boolean blueSelected = false;

    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        font = Typeface.createFromAsset(this.getAssets(), "fonts/ELECT.TTF");

        storeDialog = new Dialog(this);
        pauseDialog = new Dialog(this);

        redTeamCoinsText = findViewById(R.id.redteam_coins_text);
        blueTeamCoinsText = findViewById(R.id.blueteam_coins_text);

        redTeamLife = findViewById(R.id.redteam_life_text);
        blueTeamLife = findViewById(R.id.blueteam_life_text);

        redTeamCoinsText.setTypeface(font);
        blueTeamCoinsText.setTypeface(font);
        redTeamLife.setTypeface(font);
        blueTeamLife.setTypeface(font);

        gameLoop.addCoinsOfTime(this, redTeamCoinsText, blueTeamCoinsText, redTeamLife, blueTeamLife);

        redTeamGrid = findViewById(R.id.redteam_grid);
        blueTeamGrid = findViewById(R.id.blueteam_grid);

        redAdapter = new ImageAdapter(this);
        blueAdapter = new ImageAdapter(this);
        storeAdapter = new StoreItemsAdapter(this);

        redTeamGrid.setAdapter(redAdapter);
        blueTeamGrid.setAdapter(blueAdapter);

        redTeamGrid.setOnItemClickListener(redTeamGridOnClickListener);
        blueTeamGrid.setOnItemClickListener(blueTeamGridOnClickListener);

        pauseButton = findViewById(R.id.btn_pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLoop.pause();
                createPauseDialog();
            }
        });
    }

    private GridView.OnItemClickListener redTeamGridOnClickListener = new GridView.OnItemClickListener(){
        long backpressedTime;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if ((int)redAdapter.getItem(position) == R.drawable.empty){
                selectedPosition = position;
                blueSelected = false;
                gameLoop.pause();
                storeAdapter.setTeam(blueSelected);
                createStoreDialog();
            } else{
                if (backpressedTime + 2000 > System.currentTimeMillis()){
                    int pos = (int) storeAdapter.getItemId((Integer) redAdapter.getItem(position));
                    redAdapter.setNewValue(position, R.drawable.empty);
                    redTeamGrid.setAdapter(redAdapter);
                    redTeamCoinsText.setText(String.valueOf(Integer.parseInt(redTeamCoinsText.getText().toString()) + (Integer.parseInt(storeAdapter.costs[pos]) / 2)));
                    return;
                } else
                    Toast.makeText(GameActivity.this, "Нажмите еще раз для продажи", Toast.LENGTH_SHORT).show();

                backpressedTime = System.currentTimeMillis();
            }
        }
    };

    private GridView.OnItemClickListener blueTeamGridOnClickListener = new GridView.OnItemClickListener(){
        long backpressedTime;
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            blueSelected = true;
            if ((int)blueAdapter.getItem(position) == R.drawable.empty){
                selectedPosition = position;
                gameLoop.pause();
                storeAdapter.setTeam(blueSelected);
                createStoreDialog();
            } else{
                if (backpressedTime + 2000 > System.currentTimeMillis()){
                    int pos = (int) storeAdapter.getItemId((Integer) blueAdapter.getItem(position));
                    blueAdapter.setNewValue(position, R.drawable.empty);
                    blueTeamGrid.setAdapter(blueAdapter);
                    blueTeamCoinsText.setText(String.valueOf(Integer.parseInt(blueTeamCoinsText.getText().toString()) + (Integer.parseInt(storeAdapter.costs[pos]) / 2)));
                    return;
                } else
                    Toast.makeText(GameActivity.this, "Нажмите еще раз для продажи", Toast.LENGTH_SHORT).show();

                backpressedTime = System.currentTimeMillis();

            }

        }
    };

    private GridView.OnItemClickListener storeGridOnClickListener = new GridView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (blueSelected){
                if (Integer.parseInt(blueTeamCoinsText.getText().toString()) < Integer.parseInt(storeAdapter.costs[position]))
                    Toast.makeText(GameActivity.this, "Недостаточно средств!", Toast.LENGTH_SHORT).show();
                else{
                    blueAdapter.setNewValue(selectedPosition, storeAdapter.blueStore[position]);
                    blueTeamCoinsText.setText(String.valueOf(Integer.parseInt(blueTeamCoinsText.getText().toString()) - Integer.parseInt(storeAdapter.costs[position])));
                }
            }
            else {
                if (Integer.parseInt(redTeamCoinsText.getText().toString()) < Integer.parseInt(storeAdapter.costs[position]))
                    Toast.makeText(GameActivity.this, "Недостаточно средств!", Toast.LENGTH_SHORT).show();
                else{
                    redAdapter.setNewValue(selectedPosition, storeAdapter.redStore[position]);
                    redTeamCoinsText.setText(String.valueOf(Integer.parseInt(redTeamCoinsText.getText().toString()) - Integer.parseInt(storeAdapter.costs[position])));
                }
            }

            redTeamGrid.setAdapter(redAdapter);
            blueTeamGrid.setAdapter(blueAdapter);
            gameLoop.returnGameLoop();
            storeDialog.dismiss();
        }
    };

    private void createStoreDialog() {
        storeDialog.setContentView(R.layout.store_dialog);
        storeGrid = storeDialog.findViewById(R.id.grid_dialog2);
        storeGrid.setAdapter(storeAdapter);
        storeGrid.setOnItemClickListener(storeGridOnClickListener);

        Button btnCloseStore = storeDialog.findViewById(R.id.btn_dialog_cancel);
        btnCloseStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameLoop.returnGameLoop();
                storeDialog.dismiss();
            }
        });

        if (blueSelected){
            TextView storeCoins = storeDialog.findViewById(R.id.coins_dialog2);
            storeCoins.setText(blueTeamCoinsText.getText());
            storeCoins.setTypeface(font);
        } else {
            TextView storeCoins = storeDialog.findViewById(R.id.coins_dialog2);
            storeCoins.setText(redTeamCoinsText.getText());
            storeCoins.setTypeface(font);
        }

        TextView storeText = storeDialog.findViewById(R.id.store_textview);
        storeText.setTypeface(font);

        // Ошибка: Если закрыть магазин нажатием на свободное пространство - главный цикл игры так и останется на паузе.

        storeDialog.show();
    }

    private void createPauseDialog () {
        pauseDialog.setContentView(R.layout.pause_dialog);
        Button btnContinue = pauseDialog.findViewById(R.id.btn_continue);
        Button btnHome = pauseDialog.findViewById(R.id.btn_home);
        TextView textPause = pauseDialog.findViewById(R.id.pause_textview);

        textPause.setTypeface(font);
        btnContinue.setTypeface(font);
        btnHome.setTypeface(font);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseDialog.dismiss();
                gameLoop.returnGameLoop();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, MainActivity.class));
                gameLoop.pause();
                finish();
            }
        });

        // Ошибка: Если закрыть меню паузы нажатием на свободное пространство - главный цикл игры так и останется на паузе.

        pauseDialog.show();
    }
}