package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivitiy extends AppCompatActivity {
PatitoAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activitiy);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.24:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Button registro = findViewById(R.id.registro);
        final EditText username = findViewById(R.id.usernme);
        final EditText passwrd = findViewById(R.id.passwrd);
        final EditText cpass = findViewById(R.id.cpaswrd);
        final EditText nme = findViewById(R.id.Name);
        final EditText lname  = findViewById(R.id.LastName);
        final EditText email = findViewById(R.id.email);
        api = retrofit.create(PatitoAPI.class);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().isEmpty()||passwrd.getText().toString().trim().isEmpty()||
                        cpass.getText().toString().trim().isEmpty()||nme.getText().toString().trim().isEmpty()|| lname.getText().toString().trim().isEmpty()
                        ||email.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUpActivitiy.this, "Por favor, llene todos los campos para continnuar", Toast.LENGTH_SHORT).show();
                }else if(passwrd.getText().toString().trim().equals(cpass.getText().toString().trim())){
                            if(email.getText().toString().contains("@") && email.getText().toString().contains(".com") && email.getText().toString().trim().length()>6) {
                                user newUser = new user(nme.getText().toString().trim(), username.getText().toString().trim(), passwrd.getText().toString().trim(),
                                        email.getText().toString().trim(), lname.getText().toString().trim());
                                SignUp(newUser);
                                username.setText("");
                                passwrd.setText("");
                                cpass.setText("");
                                nme.setText("");
                                lname.setText("");
                                email.setText("");
                            }else{
                                Toast.makeText(SignUpActivitiy.this, "Ingrese un email valido", Toast.LENGTH_SHORT).show();
                            }

                }else{
                    Toast.makeText(SignUpActivitiy.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected  void SignUp(user newUser){
        Call<ResponseBody> call = api.signUp(newUser);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==201){
                    Toast.makeText(SignUpActivitiy.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivitiy.this,LoginActivity.class);
                    SignUpActivitiy.this.startActivity(intent);
                }else if(response.code() == 409){
                    Toast.makeText(SignUpActivitiy.this, "Ya existe dicho usuario", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUpActivitiy.this, "Error:"+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivitiy.this, "Error:" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
