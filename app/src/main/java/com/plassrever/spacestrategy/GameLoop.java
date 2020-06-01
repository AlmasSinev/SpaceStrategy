package com.plassrever.spacestrategy;

import android.os.Handler;
import android.widget.TextView;

public class GameLoop {

    boolean running = true;
    int prevCoins;

    public void addCoinsOfTime(final TextView redText, final TextView blueText){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                prevCoins = Integer.parseInt(redText.getText().toString());
                redText.setText(String.valueOf(prevCoins + 1));

                prevCoins = Integer.parseInt(blueText.getText().toString());
                blueText.setText(String.valueOf(prevCoins + 1));

                handler.postDelayed(this, 300);
            }
        });
    }
}
