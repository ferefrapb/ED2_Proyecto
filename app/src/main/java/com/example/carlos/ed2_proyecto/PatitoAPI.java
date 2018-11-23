package com.example.carlos.ed2_proyecto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PatitoAPI {
    @GET("users/authorize")
    Call<String> validateUser();
    @POST("users/signup")
    Call<ResponseBody> signUp(@Body user newuser);
    @POST("users/signin")
    Call<ResponseBody> signIn(@Body user loggeduser);
    @GET("users/authorize")
    Call<ResponseBody> Authorize(@Header("authorization") String token);
    @GET("users/all")
    Call<List<contacto>> getusers(@Header("authorization") String token);


}
