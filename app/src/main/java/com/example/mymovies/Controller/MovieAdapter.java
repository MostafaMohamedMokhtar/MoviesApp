package com.example.mymovies.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.Model.Movie;
import com.example.mymovies.R;
import com.example.mymovies.View.DetailsActivity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Movie> moviesList ;
    private Context context ;

    public MovieAdapter( Context context, List<Movie> moviesList) {
        this.context = context ;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row , parent , false);
        return new MyViewHolder(view);
    } // end onCreateViewHolder()

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.image.setImageResource(movie.getImage());
    } // end onBindViewHolder()

    @Override
    public int getItemCount() {
        return moviesList.size();
    } // end getItemCount()

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title , genre , year ;
        ImageView image ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title_id);
            genre = itemView.findViewById(R.id.genre_id);
            year = itemView.findViewById(R.id.year_id);
            image = itemView.findViewById(R.id.imageView);
        } // end constructor

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = moviesList.get(position);
            Intent intent = new Intent(context , DetailsActivity.class);
            intent.putExtra("Title" , movie.getTitle());
            intent.putExtra("Genre" , movie.getGenre());
            intent.putExtra("Year" , movie.getYear());
            intent.putExtra("Image" , movie.getImage());
            context.startActivity(intent);
        } // end onClick()
    } // end inner class MyViewHolder(class)
} // end outer class
