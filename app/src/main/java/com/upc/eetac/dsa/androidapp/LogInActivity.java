package com.upc.eetac.dsa.androidapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {
    
    UserService userAPI;
    EditText uname;
    EditText pswrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        userAPI = ClientAPI.getClient().create(UserService.class);

        uname= (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);

    }

    public void sendLogin(View view) {
        uname= (EditText) findViewById(R.id.user);
        pswrd = (EditText) findViewById(R.id.password);
        String username = uname.getText().toString();
        String password = pswrd.getText().toString();

        Call<User> call= userAPI.loginUser(new User (username,password,""));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("TAG",response.code()+"");
                if(response.code()==201){
                    User usuario = response.body();
                    String pswrd=usuario.getPassword();
                    String uname=usuario.getUsername();
                    Integer id=usuario.getIdUser();
                    Log.d("Usuario",uname+" "+pswrd+" "+id);
                }
                else{
                    Log.d("Error","Login failed");
                    Toast toast = Toast.makeText(getApplicationContext(),"Login failed! Please try again", Toast.LENGTH_LONG);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toast.show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("Error","Failure");
            }
        });

    }
    }
