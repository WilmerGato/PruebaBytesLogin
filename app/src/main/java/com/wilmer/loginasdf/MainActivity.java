package com.wilmer.loginasdf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Método botón para registro
    public void Registrar(View view){
        Intent registrar = new Intent(this, RegistoUsuario.class);
        startActivity(registrar);
       // finish();
    }

}
