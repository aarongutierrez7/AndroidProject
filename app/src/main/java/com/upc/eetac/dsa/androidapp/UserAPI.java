package com.upc.eetac.dsa.androidapp;

import java.util.List;

import models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("/dsaApp/registroUsuario")
    Call<User> addUser(@Body User user);

    @POST("/dsaApp/checkLogin")
    Call<User> loginUser(@Body User user);

    @GET ("/dsaApp/{userID}")
    Call<User> getUser(@Path("userID") String userID);

    @GET ("/dsaApp/listaUsuarios")
    Call<User> getUserList();

    @PATCH("/dsaApp/{userID}/UpdateMonedas")
    Call<User> updateCoins(@Path("userID") String userID,@Body User user);

}
