package com.wilmer.loginasdf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wilmer.loginasdf.adapter.MovieAdapter;
import com.wilmer.loginasdf.model.Movie;
import com.wilmer.loginasdf.network.ApiClient;
import com.wilmer.loginasdf.network.ApiMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private TextView txtUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("USUARIO");

        txtUsuario = findViewById(R.id.tvUsuarioInicio);
        txtUsuario.setText("Usuario: " + usuario);

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        showMovies();
    }
    public void showMovies(){
        Call<List<Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies = response.body();
                    movieAdapter = new MovieAdapter(movies, getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(Inicio.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
