package com.upc.eetac.dsa.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    }

    public void consultarUsuario (View view){
        Intent intent = new Intent(this, ConsultarActivity.class);
        startActivity(intent);
    }

    public void editarUsuario (View view){
        Intent intent = new Intent(this,EditarActivity.class);
        startActivity(intent);
    }

    public void eliminarUsuario (View view){
        Intent intent = new Intent(this,EditarActivity.class);
        startActivity(intent);
    }
}