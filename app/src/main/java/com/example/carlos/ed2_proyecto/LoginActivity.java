package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    PatitoAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.7:3001")
                .addConverterFactory(GsonConverterFactory.create()).build();
         api = retrofit.create(PatitoAPI.class);
         final Button signup = findViewById(R.id.registro_);
         final EditText username = findViewById(R.id.usernme);
         final Button signin = findViewById(R.id.iniciosesion);
         final EditText password = findViewById(R.id.passwrd);
         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final Intent case1 = new Intent(LoginActivity.this,SignUpActivitiy.class);
                 LoginActivity.this.startActivity(case1);
                 LoginActivity.this.finish();
             }
         });
         signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(username.getText().toString().isEmpty()|| password.getText().toString().isEmpty()){
                     Toast.makeText(LoginActivity.this, "Por favor ingrese sus datos", Toast.LENGTH_SHORT).show();
                 }else{
                        user luser = new user(null,username.getText().toString(),password.getText().toString(),null,null);
                        SignIn(luser);
                        LoginActivity.this.finish();
                 }
             }
         });
    }
    protected void SignIn(final user luser){
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        Call<ResponseBody> call = api.signIn(luser);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = Preferences.edit();
                        String jwToken = "";
                        String userlogged = luser.getUserName();
                    if (response.body() != null) {
                        try {
                            jwToken = response.body().string();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    jwToken = jwToken.replace("\"","");
                        jwToken = "Bearer" +" "+ jwToken;
                        editor.putString("JWT",jwToken);
                        editor.putString("UserLoged",userlogged);
                    editor.apply();
                        AppStart();
                    Toast.makeText(LoginActivity.this, "Sesión Iniciada", Toast.LENGTH_SHORT).show();
                }else if(response.code() == 404){
                    Toast.makeText(LoginActivity.this, "No existe este usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "ERROR:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void AppStart(){
        Intent intent = new Intent(LoginActivity.this,chatactivity.class);
        LoginActivity.this.startActivity(intent);
    }



}
