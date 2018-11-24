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
import retrofit2.http.PUT;
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
    @GET("messages/validate")
    Call<Conversation> Validateconvese(@Header("authorization") String token,@Header("ouser")String user);
    @POST("messages/addnew")
    Call<ResponseBody> AgregarConversacion(@Header("authorization") String token,@Body Conversation conversation);
    @PUT("messages/update")
    Call<ResponseBody> Updatechat(@Header("authorization") String token);
    @GET("messages/getConversation")
    Call<Conversation> getConversation(@Header("authorization")String token,@Header("user") String user);
    @GET("messages/getAll")
    Call<List<Conversation>> getConversations(@Header("authorization") String token);


}
