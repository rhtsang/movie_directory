package com.example.mymoviedirectory.mymoviedirectory.Util;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by RaymondTsang on 12/28/17.
 */

public class Preferences {
    SharedPreferences sharedPreferences;

    public Preferences(Activity activity) {
        sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void setSearch(String search) {
        sharedPreferences.edit().putString("search", search).commit();
    }

    public String getSearch() {
        return sharedPreferences.getString("search", "Alien");
    }
}
