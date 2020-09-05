package com.example.eggtimerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private ImageView imageView;
    private TextView textView;
    private Button button;
    private CountDownTimer countDownTimer;
    private boolean isCounting = false;

    public void reset() {

        countDownTimer.cancel();
        seekBar.setEnabled(true);
        button.setText("Go!");
        isCounting = false;
        seekBar.setProgress(30);
        textView.setText(conversable(30));


    }

    public void start(final View view) {

        if (isCounting) {
            reset();
        } else {
            seekBar.setEnabled(false);
            button.setText("Stop");
            isCounting = true;

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    textView.setText(conversable((int) millisUntilFinished / 1000));

                }

                @Override
                public void onFinish() {


                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.birds)
                    mediaPlayer.start();
                    reset();


                }
            }.start();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        seekBar.setProgress(30);


        textView.setText("0:30");
        seekBar.setMax(10 * 60);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(conversable(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private String conversable(int progress) {
        int leftSeconds = progress % 60;
        int minutes = progress / 60;
        if (leftSeconds < 10) {
            return minutes + ":0" + leftSeconds;
        }

        return minutes + ":" + leftSeconds;
    }


}
