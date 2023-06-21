package com.batuhanerkul.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView textView,textView2;
    Button button;
    ImageView imageView;
    int numberTime = 10;
    int numberPoint = 0;
    Handler handler;
    Runnable runnable;
    float x,y;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        imageView.setVisibility(View.INVISIBLE);

    }

    public void start(View view){
        imageView.setVisibility(View.VISIBLE);
        imageMove();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (numberTime>1){
                    textView.setText("Point: " + numberPoint);
                    textView2.setText("Time: " + numberTime);
                    numberTime --;
                    textView2.setText("Time: " + numberTime);
                    handler.postDelayed(runnable,1000);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imageView.setClickable(true);
                            numberPoint ++;
                            textView.setText("Point: " + numberPoint);
                        }
                    });
                }else{
                    handler.removeCallbacks(runnable);
                    numberTime=0;
                    imageView.setClickable(false);
                    textView2.setText("Time: " + numberTime);
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        };
        handler.post(runnable);
        button.setVisibility(View.INVISIBLE);
    }
    public void imageMove(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Timer myTimer = new Timer();
        TimerTask move =new TimerTask() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                x = imageView.getX() + 10;
                y = imageView.getY() + 5;
                if (x < 0) {
                    x = 0;
                } else if (x > screenWidth - imageView.getWidth()) {
                    x = screenWidth - imageView.getWidth();
                }
                if (y < 0) {
                    y = 0;
                } else if (y > screenHeight - imageView.getHeight()) {
                    y = screenHeight - imageView.getHeight();
                }
                imageView.setX(x);
                imageView.setY(y);
            }
        };
        myTimer.schedule(move,0,100);

    }
}


//TranslateAnimation animation = new TranslateAnimation(0, 100, 0, 0);
//animation.setDuration(1000); // Animasyonun süresi 1000 milisaniye (1 saniye)
//animation.setRepeatCount(0); // Animasyonu tekrarlamayacak
//imageView.startAnimation(animation);
