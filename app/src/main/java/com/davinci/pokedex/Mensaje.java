package com.davinci.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mensaje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        TextView t = new TextView(this);
        setContentView(t);
        t.setText("Bienvenido " + user);

    }


}
