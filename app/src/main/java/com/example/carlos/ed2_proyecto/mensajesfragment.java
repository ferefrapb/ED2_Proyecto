package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;

public class mensajesfragment extends Fragment {
    RecyclerView myrview;
    PatitoAPI api;
    msajesfragAdapter adapter;
    Button logout1;
    List<String> conversationOther = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_mensajes, container, false);
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        myrview = view.findViewById(R.id.myrview);
        String Token = Preferences.getString("JWT","idk");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.24:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(PatitoAPI.class);
        logout1 = view.findViewById(R.id.logout1);
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences Preferences
                        = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                final SharedPreferences.Editor editor = Preferences.edit();
                editor.putString("JWT",null);
                returnSignIn();
            }
        });
        if(Token.equals("idk")) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        }else{
            String token = Preferences.getString("JWT","idk");
            if(!token.equals("idk")){
                getConversations(token);
            }


        }
        return view;

    }
    protected  void getConversations(String token){
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Call<List<Conversation>> call = api.getConversations(token);
        final SharedPreferences.Editor editor = Preferences.edit();
        call.enqueue(new Callback<List<Conversation>>() {
            @Override
            public void onResponse(Call<List<Conversation>> call, Response<List<Conversation>> response) {
                if(response.isSuccessful()){
                    String userloged = Preferences.getString("UserLoged","idk");
                    String tken = "";
                    okhttp3.Headers headers = response.headers();
                    tken = headers.get("authorization");
                    tken = tken.replace("\"","");
                    tken = "Bearer" +" " + tken;
                    editor.putString("JWT",tken);
                    editor.apply();
                    if(response.body()!= null){
                        List<Conversation> convers = response.body();
                        for(Conversation c:convers){
                            if(c.participantes.get(0).equals(userloged)){
                                conversationOther.add(c.participantes.get(1));
                            }else{
                                conversationOther.add(c.participantes.get(0));
                            }
                        }
                        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getActivity());
                        myrview.setLayoutManager(mLayoutmanager);
                        adapter = new msajesfragAdapter(getActivity(),conversationOther);
                        myrview.setAdapter(adapter);
                        adapter.setOnclickListener(new msajesfragAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClicked(int position) {
                                String uconverse = conversationOther.get(position);
                                editor.putString("Userconverse",uconverse);
                                Intent intent = new Intent(getActivity(),mensajesactivity.class);
                                startActivity(intent);
                                editor.apply();

                            }
                        });
                    }

                }else if(response.code() == 403){
                    editor.putString("JWT","");
                    editor.apply();
                    returnSignIn();
                    Toast.makeText(getActivity(), "Sesión expirada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Conversation>> call, Throwable t) {
                Toast.makeText(getActivity(), "ERROR:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void returnSignIn(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }

}
