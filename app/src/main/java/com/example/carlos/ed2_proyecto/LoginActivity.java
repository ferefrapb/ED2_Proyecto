package com.example.carlos.ed2_proyecto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://190.149.42.147:3001/")
                .addConverterFactory(GsonConverterFactory.create()).build();
         api = retrofit.create(PatitoAPI.class);
         final Button signup = findViewById(R.id.registro_);
         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                    openSignUp();
             }
         });
    }
    private void openSignUp(){
        signupFragment fragment = new signupFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fracontainer,fragment,"signupFragment").commit();
    }



}
