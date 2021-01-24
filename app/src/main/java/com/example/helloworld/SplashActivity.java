package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

//    variables
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        Animations
        topAnim=AnimationUtils.loadAnimation(this,R.anim.image_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.text_animation);

//        Hooks
        image=findViewById(R.id.imageView);
        tv=findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        tv.setAnimation(bottomAnim);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).start();

    }
}