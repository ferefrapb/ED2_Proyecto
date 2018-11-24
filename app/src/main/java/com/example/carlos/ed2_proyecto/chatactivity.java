package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class chatactivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    PatitoAPI api;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        if(savedInstanceState== null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new mensajesfragment()).commit();
            Toast.makeText(this, "Mensajes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch ((item.getItemId())){
                case R.id.nav_mensajes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new mensajesfragment()).commit();
                    break;
                case R.id.nav_contactos:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new contactosfragment()).commit();
                    break;
            }
        return true;
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
