
package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carlos.ed2_proyecto.Algorithm.SDES;
import com.example.carlos.ed2_proyecto.Algorithm.ZigZag;

import java.util.Objects;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mensajesactivity extends AppCompatActivity {
    PatitoAPI api;
    ListView listView;
    ImageButton button;
    EditText editText;
    Conversation conversation;
    messageAdapter adapter;
    FloatingActionButton buttonf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        listView = findViewById(R.id.lvMensajes);
        button = findViewById(R.id.imageButton);
        editText = findViewById(R.id.etMensaje);
        buttonf = findViewById(R.id.fab);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.24:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(PatitoAPI.class);
            obtenerConversacion();
            buttonf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obtenerConversacion();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mensaje = editText.getText().toString();
                    if(mensaje.isEmpty()){
                        Toast.makeText(mensajesactivity.this, "vacío", Toast.LENGTH_SHORT).show();
                    }else{
                        final SharedPreferences myPreferences
                                = PreferenceManager.getDefaultSharedPreferences(mensajesactivity.this);
                        String user = myPreferences.getString("Userconverse","idk");
                        final String userlog = myPreferences.getString("UserLoged","idk");
                        ZigZag algorithm = new ZigZag();
                        String message_;

                        try {
                            message_ = algorithm.Encryption(mensaje,conversation.key);
                            Message message = new Message(userlog,user,message_,"");
                            conversation.mensajes.add(message);
                            editText.setText("");
                            Enviar();
                            adapter = new messageAdapter(mensajesactivity.this,userlog,conversation);
                            listView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }
            });


    }
    protected void obtenerConversacion(){
        final SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(mensajesactivity.this);
        String token = myPreferences.getString("JWT", "idk");
        String user = myPreferences.getString("Userconverse","idk");
        final String userlog = myPreferences.getString("UserLoged","idk");
        Call<Conversation> call = api.getConversation(token,user);
        final SharedPreferences.Editor editor = myPreferences.edit();
        call.enqueue(new Callback<Conversation>() {
            @Override
            public void onResponse(Call<Conversation> call, Response<Conversation> response) {
                if(response.isSuccessful()){
                    String tken = "";
                    Headers headers = response.headers();
                    tken = headers.get("authorization");
                    tken = tken.replace("\"","");
                    tken = "Bearer" +" " + tken;
                    editor.putString("JWT",tken);
                    editor.apply();
                    conversation = response.body();
                    adapter = new messageAdapter(mensajesactivity.this,userlog,conversation);
                    listView.setAdapter(adapter);
                }else{
                    returnSignIn();
                }
            }

            @Override
            public void onFailure(Call<Conversation> call, Throwable t) {
                Toast.makeText(mensajesactivity.this, "ERROR"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void returnSignIn(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

    }
    protected void Enviar(){
        final SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(mensajesactivity.this);
        String token = myPreferences.getString("JWT", "idk");
        String user = myPreferences.getString("Userconverse","idk");
        final String userlog = myPreferences.getString("UserLoged","idk");
        Call<ResponseBody> call = api.Updatechat(token,user,conversation);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = myPreferences.edit();
                    String tken = "";
                    Headers headers = response.headers();
                    tken = headers.get("authorization");
                    tken = tken.replace("\"","");
                    tken = "Bearer" +" " + tken;
                    editor.putString("JWT",tken);
                    editor.apply();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mensajesactivity.this, "ERROR"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
