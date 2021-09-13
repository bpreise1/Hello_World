package com.example.cookieclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.cookieclicker.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int oreoBonus = 0;
    private int cowBonus = 0;
    private int score = 500;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void oreoClick(View view) {
        score = score + 1 + oreoBonus;
        binding.score.setText("Score: " + score);
    }


    public void milkClick(View view) {
        if(score >= 100) {
            score -= 100;
            oreoBonus++;
            binding.score.setText("Score: " + score);
            binding.buy.setText("Buy for one extra point each click! (Owned: " + oreoBonus + ")");
        }
    }

    public void cowClick(View view) {
        if(score >= 250) {
            score -= 250;
            binding.score.setText("Score: " + score);
            if(flag == 1) {
                cowBonus += 5;
            }
            else {
                flag = 1;
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        score += 5 + cowBonus;
                        runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                binding.score.setText("Score: " + score);
                            }
                        });
                    }
                }, 1000, 2000);
            }
        }
    }
}
