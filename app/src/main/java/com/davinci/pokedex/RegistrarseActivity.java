package com.davinci.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.davinci.pokedex.controller.Registrar;

public class RegistrarseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }

    public void OnClickLogOn(View view) {
        TextView tMail = findViewById(R.id.mail);
        String mail = tMail.getText().toString();

        TextView tName = findViewById(R.id.name);
        String name = tName.getText().toString();

        TextView tPass = findViewById(R.id.pass);
        String pass = tPass.getText().toString();
        Registrar registrar = new Registrar(mail,name,pass,getApplicationContext());

    }
}