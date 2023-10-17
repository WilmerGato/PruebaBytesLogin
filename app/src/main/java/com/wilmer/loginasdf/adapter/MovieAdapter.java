package com.wilmer.loginasdf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wilmer.loginasdf.R;
import com.wilmer.loginasdf.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_name.setText(movies.get(position).getName());
        Glide.with(context).load(movies.get(position).getType()).into(holder.iv_portada);
    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_portada;
        private TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada = itemView.findViewById(R.id.iv_portada);
            tv_name = itemView.findViewById(R.id.tv_titulo);
        }
    }
}
