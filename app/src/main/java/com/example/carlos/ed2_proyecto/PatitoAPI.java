package com.example.carlos.ed2_proyecto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PatitoAPI {
    @GET("users")
    Call<List<user>> getusers();
    @GET("users/authorize")
    Call<String> validateUser();
    @POST("users/signup")
    Call<ResponseBody> signUp(@Body user newuser);




}
