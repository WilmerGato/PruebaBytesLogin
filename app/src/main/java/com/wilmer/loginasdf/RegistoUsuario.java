package com.wilmer.loginasdf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        mfirestore = FirebaseFirestore.getInstance();

        btn_Guardar = findViewById(R.id.btnGuardar);
        usuarioAdd = findViewById(R.id.tvUsuarioAdd);
        passwordAdd = findViewById(R.id.ptvPasswordAdd);

        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = usuarioAdd.getText().toString().trim();
                String pass = passwordAdd.getText().toString().trim();
                String email = nombreUsuario + "@gmail.com";

                if (nombreUsuario.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
                }
                else if(nombreUsuario.length() < 8){
                    Toast.makeText(getApplicationContext(), "El nombre de usuario debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length() < 6){
                    Toast.makeText(getApplicationContext(), "La contraseña debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                }
                else if (!validarMayus(pass)){
                    Toast.makeText(getApplicationContext(), "La contraseña debe contener al menos una letra mayúscula.", Toast.LENGTH_SHORT).show();
                }
                else {
                    posUsuario(nombreUsuario, pass, email);
                }
            }
        });
    }

    private boolean validarMayus(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private void posUsuario(String nombreUsuario, String pass, String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("Usuario", nombreUsuario);
        map.put("Password", pass);
        map.put("Email", email);

        mfirestore.collection("usuarios")
                .add(map)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Error al registrarse: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                });
    }

    // Método botón regresar
    public void Regresar(View view) {
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}
