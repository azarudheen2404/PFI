package in.co.esquareinfo.pfi.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by azar on 09-07-2017.
 */

public class MyPreference {

    private static final String PREF_SURVEY = "survey";
    private static final String ID = "id";
    private SharedPreferences sharedPreferences;

    public MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_SURVEY, Context.MODE_PRIVATE);
    }

    public int getId() {
        return sharedPreferences.getInt(ID, -1);
    }

    public void setId(int id){
        sharedPreferences.edit().putInt(ID,id).apply();
    }

    public void incrementId() {
        int id = getId() + 1;
        sharedPreferences.edit().putInt(ID, id).apply();
    }
}
