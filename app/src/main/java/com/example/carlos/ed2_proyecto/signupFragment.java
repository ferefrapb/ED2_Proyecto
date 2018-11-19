package com.example.carlos.ed2_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signupFragment extends Fragment {
private PatitoAPI api;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup,container,false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.14:3001")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final Button  registro = view.findViewById(R.id.registro);
        final EditText usernme = view.findViewById(R.id.usernme);
        final EditText password = view.findViewById(R.id.passwrd);
        final EditText cpassword = view.findViewById(R.id.cpaswrd);
        final EditText nme = view.findViewById(R.id.Name);
        final EditText Lastnme = view.findViewById(R.id.LastName);
        final EditText email = view.findViewById(R.id.email);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().equals(cpassword.getText())){
                    SignUp(usernme.getText().toString(),password.getText().toString(),
                            nme.getText().toString(),Lastnme.getText().toString(),email.getText().toString());
                }else{
                    Toast.makeText(getActivity(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }
        });
        api = retrofit.create(PatitoAPI.class);
        return view;
    }
    protected void SignUp(String user, String password, String name, String Lstname, String email){
        user newuser = new user(name,user,password,email,Lstname);
       Call<ResponseBody> call =  api.signUp(newuser);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                    BacktoSignIn();
                }else if(response.code()==409){
                    Toast.makeText(getContext(), "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Error " + response.code(),Toast.LENGTH_SHORT).show();
                }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Toast.makeText(getContext(), "ERROR:" + t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }
    protected void BacktoSignIn(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
    }
}
