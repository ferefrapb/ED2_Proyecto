package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         img = (ImageView) findViewById(R.id.imgview);
         Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
         img.startAnimation(myanim);
       final Intent case1 = new Intent(this,LoginActivity.class);
         Thread timer = new Thread(){
             public void run(){
                 try{
                  sleep(5000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 finally {
                        startActivity(case1);
                        finish();
                 }
             }
         };
         timer.start();

    }
}
