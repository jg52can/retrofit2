package com.example.myretrofitapp.Retrofit;

import com.example.myretrofitapp.Models.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("loginuser.php")
    Call<UserModel> loginUser(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("registeruser.JS")
    Call<UserModel> registerUser(@Field("email") String email,
                                 @Field("name") String name,
                                 @Field("password") String password,
                                 @Field("phone") String phone);


}
