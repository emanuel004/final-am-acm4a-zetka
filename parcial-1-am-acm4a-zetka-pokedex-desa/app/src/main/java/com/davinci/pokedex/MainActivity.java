package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.boton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                TranslateAnimation animation = new TranslateAnimation(0,0,0,-1500);
                animation.setDuration(1000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());

                LinearLayout linear = findViewById(R.id.linearUp);

                TranslateAnimation animation1 = new TranslateAnimation(0,0,0,1500);
                animation1.setDuration(1000);
                animation1.setInterpolator(new AccelerateDecelerateInterpolator());

                LinearLayout linear2 = findViewById(R.id.linearDown);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        linear.setVisibility(View.VISIBLE);
                        linear2.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        linear.setVisibility(View.INVISIBLE);
                        linear2.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                linear.startAnimation(animation);
                linear2.startAnimation(animation1);
                button.startAnimation(animation1);

            }
        });

        /*Button cancel = findViewById(R.id.cancelar);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation animation = new TranslateAnimation(0,0,-1500,0);
                animation.setDuration(1000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());

                LinearLayout linear = findViewById(R.id.linearUp);

                TranslateAnimation animation1 = new TranslateAnimation(0,0,1500,0);
                animation1.setDuration(1000);
                animation1.setInterpolator(new AccelerateDecelerateInterpolator());

                LinearLayout linear2 = findViewById(R.id.linearDown);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        linear.setVisibility(View.VISIBLE);
                        linear2.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                linear.startAnimation(animation);
                linear2.startAnimation(animation1);
                button.startAnimation(animation1);
            }
        });*/
    }
}