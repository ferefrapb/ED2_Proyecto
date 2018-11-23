package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class contactosfragment extends Fragment {
    RecyclerView recyclerView;
    List<String> contactos = new ArrayList<>();
    List<contacto> contactoLis;
    contactosAdapter adapter;
    PatitoAPI api;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contactos,container,false);
         contactoLis = new ArrayList<>();
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String Token = Preferences.getString("JWT","idk");
        recyclerView = view.findViewById(R.id.myRV);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.33:3001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(PatitoAPI.class);


        if(Token.equals("idk")){
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        }else{
                getUsers(Token,view);
        }

        return view;
    }
    protected void getUsers(String token, final View view){
        final SharedPreferences Preferences
                = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        Call<List<contacto>> call =     api.getusers(token);
        call.enqueue(new Callback<List<contacto>>() {
            @Override
            public void onResponse(Call<List<contacto>> call, Response<List<contacto>> response) {
                if(response.isSuccessful()){
                    final SharedPreferences.Editor editor = Preferences.edit();
                    String tken = "";
                    if(response.headers() != null){
                        try{
                            Headers headers = response.headers();
                            tken = headers.get("authorization");
                            tken = tken.replace("\"","");
                            tken = "Bearer" +" " + tken;
                            editor.putString("JWT",tken);
                            editor.apply();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                    }
                    if(response.body() !=null){
                        contactoLis = response.body();
                        String user = Preferences.getString("UserLoged","idk");
                        if (contactoLis != null) {
                            for(contacto contacto: contactoLis){
                                if(!contacto.getUsername().equals(user))
                                contactos.add(contacto.getUsername());
                            }
                        }
                        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(mLayoutmanager);
                        adapter = new contactosAdapter(getActivity(),contactos);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnclickListener(new contactosAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClicked(int position) {
                                String uconverse = contactos.get(position);
                                editor.putString("Userconverse",uconverse);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        new mensajesfragment()).addToBackStack(null).commit();
                                        chatactivity chatactivity = (chatactivity) getActivity();
                                        chatactivity.bottomNav.setSelectedItemId(R.id.nav_mensajes);
                                Toast.makeText(getActivity(), uconverse, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }else if(response.code()==403){
                    Toast.makeText(getActivity(), "Sesión expirada", Toast.LENGTH_SHORT).show();
                    returnSignIn();
                }
            }

            @Override
            public void onFailure(Call<List<contacto>> call, Throwable t) {
                Toast.makeText(getActivity(), "Sesión expirada", Toast.LENGTH_SHORT).show();
                returnSignIn();
            }
        });
    }
    void returnSignIn(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        Objects.requireNonNull(getActivity()).startActivity(intent);

    }

}

