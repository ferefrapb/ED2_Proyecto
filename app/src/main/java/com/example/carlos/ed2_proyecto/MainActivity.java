package com.example.carlos.ed2_proyecto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout myLayout;
    private final int STORAGE_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,myLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        myLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new loginfragment()).commit();
            Toast.makeText(this,"Login",Toast.LENGTH_LONG).show();
            navigationView.setCheckedItem(R.id.nav_login); }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new loginfragment()).commit();
                Toast.makeText(this,"Login",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_crea_usuario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new crearusuariofragment()).commit();
                Toast.makeText(this,"Crear Usuario",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_mensajes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new mensajesfragment()).commit();
                Toast.makeText(this,"Mensajes",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_histmensajes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new histmensajesfragment()).commit();
                Toast.makeText(this,"Historial de Mensajes",Toast.LENGTH_LONG).show();
                break;
        }
        myLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(myLayout.isDrawerOpen(GravityCompat.START)){
            myLayout.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
    }


}
