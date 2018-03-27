package com.example.mymoviedirectory.mymoviedirectory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviedirectory.mymoviedirectory.Activities.MovieDetailsActivity;
import com.example.mymoviedirectory.mymoviedirectory.Model.Movie;
import com.example.mymoviedirectory.mymoviedirectory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by RaymondTsang on 12/28/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    List<Movie> movieList;

    public RecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        TextView title;
        TextView releaseDate;
        TextView runtime;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.recyclerPosterId);
            title = (TextView) itemView.findViewById(R.id.recyclerTitleId);
            releaseDate = (TextView) itemView.findViewById(R.id.recyclerReleaseDateId);
            runtime = (TextView) itemView.findViewById(R.id.recyclerTypeId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = movieList.get(getAdapterPosition());
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        Picasso.with(context).load(movie.getPosterURL()).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);
        holder.title.setText(movie.getTitle());
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.runtime.setText(movie.getType());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
