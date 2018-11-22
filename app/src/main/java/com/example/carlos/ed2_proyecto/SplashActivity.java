package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {
private ImageView img;
PatitoAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         img = (ImageView) findViewById(R.id.imgview);
         Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
         img.startAnimation(myanim);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.33:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(PatitoAPI.class);
         Thread timer = new Thread(){
             public void run(){
                 try{

                  sleep(5000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 finally {
                     final SharedPreferences Preferences
                             = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                     String Token = Preferences.getString("JWT","idk");
                        if(!Token.equals("idk")){
                            Verify(Token);
                        }else{
                            returnSignIn();
                        }
                        finish();
                 }
             }
         };
         timer.start();

    }
    protected void Verify(String token){
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
      Call<ResponseBody> call = api.Authorize(token);
      call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              if(response.isSuccessful()){
                  Toast.makeText(SplashActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                  SharedPreferences.Editor editor = Preferences.edit();
                  String nTOken = "";
                  if(response.body()!= null){
                      try{
                          nTOken = response.body().string();
                          nTOken = nTOken.replace("\"","");
                          nTOken = "Bearer" +" " + nTOken;
                          editor.putString("JWT",nTOken);
                          editor.apply();
                          AppStart();
                      }catch (NullPointerException e){
                          e.printStackTrace();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              } else if(response.code() == 403){
                  Toast.makeText(SplashActivity.this, "Sesión expirada", Toast.LENGTH_SHORT).show();
                  returnSignIn();
              }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
              Toast.makeText(SplashActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
              returnSignIn();
          }
      });
    }
    void AppStart(){
        Intent intent = new Intent(SplashActivity.this,chatactivity.class);
        SplashActivity.this.startActivity(intent);
    }
    void returnSignIn(){
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            SplashActivity.this.startActivity(intent);

    }

}
