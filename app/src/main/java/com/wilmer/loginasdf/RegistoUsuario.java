package com.wilmer.loginasdf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistoUsuario extends AppCompatActivity {

    Button btn_Guardar;
    TextView usuarioAdd, passwordAdd;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_usuario);

        this.setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();

        btn_Guardar = findViewById(R.id.btnGuardar);
        usuarioAdd = findViewById(R.id.tvUsuarioAdd);
        passwordAdd = findViewById(R.id.ptvPasswordAdd);

        btn_Guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nombreUsuario = usuarioAdd.getText().toString().trim();
                String pass = passwordAdd.getText().toString().trim();

                if(nombreUsuario.isEmpty() && pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
                } else {
                    posUsuario(nombreUsuario, pass);
                }
            }
        });
    }

    private void posUsuario(String nombreUsuario, String pass) {

        Map<String, Object> map = new HashMap<>();

        map.put("Usuario", usuarioAdd);
        map.put("Password", pass);
        mfirestore.collection("usuarios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al registrarse!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Método botón regresar
    public void Regresar(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}