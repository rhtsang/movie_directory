package com.example.mymoviedirectory.mymoviedirectory.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymoviedirectory.mymoviedirectory.Model.Movie;
import com.example.mymoviedirectory.mymoviedirectory.R;
import com.example.mymoviedirectory.mymoviedirectory.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private ImageView poster;
    private TextView title;
    private TextView releaseDate;
    private TextView category;
    private TextView rating;
    private TextView runtime;
    private TextView directors;
    private TextView actors;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        requestQueue = Volley.newRequestQueue(this);

        setupUI();

        Movie movie = (Movie) getIntent().getExtras().getSerializable("movie");
        //Movie movie = getIntent().getSerializableExtra("movie");
        getMovieDetails(movie.getImdbId());
    }

    private void getMovieDetails(String imdbId) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.ID_URL + imdbId + Constants.API_KEY,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Picasso.with(getApplicationContext()).load(response.getString("Poster")).placeholder(android.R.drawable.ic_btn_speak_now).into(poster);
                            title.setText(response.getString("Title"));
                            releaseDate.setText(response.getString("Released"));
                            category.setText(response.getString("Type"));
                            JSONArray array = response.getJSONArray("Ratings");
                            /*if (array.length() > 0) {
                                JSONObject object = array.getJSONObject(0);
                                rating.setText(object.getString("Source") + ": " + object.getString("Value"));
                            } else {
                                rating.setText("N/A");
                            }*/
                            rating.setText(array.getJSONObject(0).getString("Source") + ": " +
                                array.getJSONObject(0).getString("Value"));
                            runtime.setText(response.getString("Runtime"));
                            directors.setText("Directed by: " + response.getString("Director"));
                            actors.setText("Actors: " + response.getString("Actors"));
                            writers.setText("Written by: " + response.getString("Writer"));
                            plot.setText("Plot: " + response.getString("Plot"));
                            boxOffice.setText("Box Office: " + response.getString("BoxOffice"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void setupUI() {
        poster = (ImageView) findViewById(R.id.detailsPosterId);
        title = (TextView) findViewById(R.id.detailsTitleId);
        releaseDate = (TextView) findViewById(R.id.detailsReleaseDateId);
        category = (TextView) findViewById(R.id.detailsCategoryId);
        rating = (TextView) findViewById(R.id.detailsRatingId);
        runtime = (TextView) findViewById(R.id.detailsRuntimeId);
        directors = (TextView) findViewById(R.id.detailsDirectorId);
        actors = (TextView) findViewById(R.id.detailsActorsId);
        writers = (TextView) findViewById(R.id.detailsWritersId);
        plot = (TextView) findViewById(R.id.detailsPlotId);
        boxOffice = (TextView) findViewById(R.id.detailsBoxOfficeId);
    }
}
