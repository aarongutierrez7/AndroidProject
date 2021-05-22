package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_AndroidApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userAPI = ClientAPI.getClient().create(UserAPI.class);

    }

    public void sendRegister(View view) {
        EditText uname = (EditText) findViewById(R.id.user);
        EditText pswrd = (EditText) findViewById(R.id.password);
        EditText emailAddress = (EditText) findViewById(R.id.emailAddress);
        String usuario = uname.getText().toString();
        String password = pswrd.getText().toString();
        String email = emailAddress.getText().toString();
        Call<User> call = userAPI.addUser(new User(usuario, password, email));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    User usuario = response.body();
                    String pswrd = usuario.getPassword();
                    String uname = usuario.getUsername();
                    Log.d("Usuario", uname + " " + pswrd);
                    Toast toast = Toast.makeText(getApplicationContext(), "Registration succesfull! Go back and sign in", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Log.d("Error", "Register failed");
                    Toast toast = Toast.makeText(getApplicationContext(), "Register failed! Please try again", Toast.LENGTH_LONG);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("Error", "Failure");

            }
        });

    }

    public void goBack(View view) {
        finish();

    }
}
