package com.plassrever.spacestrategy;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

public class GameLoop {

    private boolean running = true;
    private int prevCoins;

    private Integer[] redTeam, blueTeam;
    private int redDamage, blueDamage;

    public void addCoinsOfTime(final Context mContext, final TextView redText, final TextView blueText, final TextView redTeamLife, final TextView blueTeamLive){
        final Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {

                if (running){

                    if (Integer.parseInt(blueTeamLive.getText().toString()) < 0 || Integer.parseInt(redTeamLife.getText().toString()) < 0){
                        running = false;

                        String winnerTeam = Integer.parseInt(blueTeamLive.getText().toString()) < 0 ? "REDTEAM" : "BLUETEAM";
                        Intent intent = new Intent(mContext, EndGameActivity.class);
                        intent.putExtra("winner", winnerTeam);
                        mContext.startActivity(intent);
                    }


                    redTeam = GameActivity.redAdapter.getTeam();
                    blueTeam = GameActivity.blueAdapter.getTeam();

                    redDamage = countDamage(redTeam);
                    blueDamage = countDamage(blueTeam);

                    redTeamLife.setText(String.valueOf(Integer.parseInt(redTeamLife.getText().toString()) - (blueDamage/10)));
                    blueTeamLive.setText(String.valueOf(Integer.parseInt(blueTeamLive.getText().toString()) - (redDamage/10)));

                    prevCoins = Integer.parseInt(redText.getText().toString());
                    redText.setText(String.valueOf(prevCoins + 50));

                    prevCoins = Integer.parseInt(blueText.getText().toString());
                    blueText.setText(String.valueOf(prevCoins + 50));
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    public void pause(){
        running = false;
    }

    public void returnGameLoop(){
        running = true;
    }

    public int countDamage (Integer[] team) {
        int damage = 0;

        for (Integer p : team){
            switch (p) {
                case R.drawable.q :
                    damage += 10;
                    continue;
                case R.drawable.w :
                    damage += 20;
                    continue;
                case R.drawable.e :
                    damage += 30;
                    continue;
                case R.drawable.r :
                    damage += 40;
                    continue;
                case R.drawable.t :
                    damage += 50;
                    continue;
                case R.drawable.y :
                    damage += 60;
                    continue;
                case R.drawable.qq :
                    damage += 10;
                    continue;
                case R.drawable.ww :
                    damage += 20;
                    continue;
                case R.drawable.ee :
                    damage += 30;
                    continue;
                case R.drawable.rr :
                    damage += 40;
                    continue;
                case R.drawable.tt :
                    damage += 50;
                    continue;
                case R.drawable.yy :
                    damage += 60;
                    continue;
            }
        }
        return damage;
    }
}
