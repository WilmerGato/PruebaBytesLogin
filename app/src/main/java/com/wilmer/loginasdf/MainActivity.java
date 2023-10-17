package com.wilmer.loginasdf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {

    Button btn_Ingresar;
    TextView usuarioRead, passwordRead;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btn_Ingresar = findViewById(R.id.btnIngresar);
        usuarioRead = findViewById(R.id.tvUsuario);
        passwordRead = findViewById(R.id.ptvPassword);
    }

    //Método botón Ingresar
    public void Ingresar(View view){

        String nombreUsuario = usuarioRead.getText().toString().trim();
        String pass = passwordRead.getText().toString().trim();

        if(nombreUsuario.isEmpty() || pass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = nombreUsuario+"@gmail.com";

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent ingresar = new Intent(MainActivity.this, Inicio.class);
                    ingresar.putExtra("USUARIO", nombreUsuario);
                    startActivity(ingresar);
                } else {
                    Intent ingresar = new Intent(MainActivity.this, Inicio.class);//Solucionar error más adelante. (No inicia)
                    ingresar.putExtra("USUARIO", nombreUsuario);
                    startActivity(ingresar);
                    //Toast.makeText(MainActivity.this, "Contraseña o usuario incorrectos ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //Método botón para registro
    public void Registrar(View view){
        Intent registrar = new Intent(this, RegistoUsuario.class);
        startActivity(registrar);
    }

}