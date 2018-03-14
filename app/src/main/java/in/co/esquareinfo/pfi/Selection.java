package in.co.esquareinfo.pfi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.co.esquareinfo.pfi.app.Block;
import in.co.esquareinfo.pfi.app.District;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.State;

public class Selection extends AppCompatActivity implements View.OnClickListener {

    public Button upload,surveybtn,refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        HeaderCertificate myHeaderClass = new HeaderCertificate(this);
        myHeaderClass.handleSSLHandshake();
        initObjects();
        initCallback();


    }

    private void initObjects(){
        surveybtn = (Button) findViewById(R.id.survey);
        upload = (Button) findViewById(R.id.upload);
        refresh = (Button) findViewById(R.id.refresh);
    }

    private void initCallback(){
        surveybtn.setOnClickListener(this);
        upload.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.upload:
               // upload();
               /* try {
                    Log.d("request",databaseHandler.getJsonRequest().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                break;
            case R.id.survey:

                Intent go = new Intent(this, Survey.class);
                startActivity(go);
                break;

            case R.id.refresh:
                stdtDetails();
                blockDetails();
        }

    }

    private void stdtDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/lookup/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("StDt",response.toString());
                        editor.commit();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(Selection.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(Selection.this);
        requestQueue.add(stringRequest1on);
    }

    private void blockDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/village/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("village",response.toString());
                        editor.commit();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(Selection.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(Selection.this);
        requestQueue.add(stringRequest1on);
    }
}
