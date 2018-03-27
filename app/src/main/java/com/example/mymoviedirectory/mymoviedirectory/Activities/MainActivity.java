package com.example.mymoviedirectory.mymoviedirectory.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymoviedirectory.mymoviedirectory.Adapter.RecyclerViewAdapter;
import com.example.mymoviedirectory.mymoviedirectory.Model.Movie;
import com.example.mymoviedirectory.mymoviedirectory.R;
import com.example.mymoviedirectory.mymoviedirectory.Util.Constants;
import com.example.mymoviedirectory.mymoviedirectory.Util.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Movie> movieList;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Preferences preferences = new Preferences(MainActivity.this);
        movieList = new ArrayList<>();
        movieList = getMovieList(preferences.getSearch());

        recyclerViewAdapter = new RecyclerViewAdapter(this, movieList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void search() {
        View view = getLayoutInflater().inflate(R.layout.search_dialog, null);
        alertDialogBuilder = new AlertDialog.Builder(this);
        final EditText searchTerm = view.findViewById(R.id.searchTermId);
        final Button searchButton = view.findViewById(R.id.searchButtonId);
        alertDialogBuilder.setView(view);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences preferences = new Preferences(MainActivity.this);
                if (!searchTerm.getText().toString().isEmpty()) {
                    preferences.setSearch(searchTerm.getText().toString());
                    getMovieList(searchTerm.getText().toString());
                }
                alertDialog.dismiss();
            }
        });
    }

    private List<Movie> getMovieList(String search) {
        Log.d("url:", Constants.SEARCH_URL_LEFT + search + Constants.SEARCH_URL_RIGHT + Constants.API_KEY);
        movieList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.SEARCH_URL_LEFT + search + Constants.SEARCH_URL_RIGHT + Constants.API_KEY,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("Search");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setPosterURL(object.getString("Poster"));
                                movie.setTitle(object.getString("Title"));
                                movie.setReleaseDate(object.getString("Year"));
                                movie.setType(object.getString("Type"));
                                movie.setImdbId(object.getString("imdbID"));
                                movieList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
        return movieList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
