package in.co.esquareinfo.pfi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.co.esquareinfo.pfi.app.District;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.State;

public class BlockDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Context mContext;
    private Spinner state, district;
    private EditText blockName;
    private String txtState, txtDistrict, txtBlockName;
    private String stateDet, districtDet, distId, stateIdDis;
    private ImageView btnNext;
    private List<District> districtlist;
    private ArrayAdapter<District> dt;
    private List<State> methodlist;
    private ArrayAdapter<State> st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_details);

        HeaderCertificate myHeaderClass = new HeaderCertificate(this);
        myHeaderClass.handleSSLHandshake();
        initObjects();
        initCallback();
        stdtDetails();
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent == this.state) {
            txtState = String.valueOf(methodlist.get(state.getSelectedItemPosition()).getId());
            SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
            String stdt =pref.getString("StDt",null);

            districtlist.clear();
            JSONObject att = null;
            try {
                att= new JSONObject(stdt.toString().trim());
                String distlist = att.getString("districtList");
                Log.d("District", distlist.toString().trim());
                JSONArray district = new JSONArray(distlist.toString());
                for (int i = 0; i < district.length(); i++) {
                    JSONObject districtdata = district.getJSONObject(i);
                    String districtDet = districtdata.getString("name");
                    String distId = districtdata.getString("ID");
                    String stateIdDis = districtdata.getString("stateID");
                    if (stateIdDis.equals(txtState)) {
                        Log.d("Districttttttt", districtDet);
                        districtlist.add(new District(distId, districtDet));
                    }
                    dt.notifyDataSetChanged();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (parent == district) {
            txtDistrict = (districtlist.get(district.getSelectedItemPosition())).getDisId();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initObjects(){
        mContext = this;
        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        btnNext = (ImageView) findViewById(R.id.next);
        methodlist = new ArrayList();
        districtlist = new ArrayList();
        st = new ArrayAdapter(mContext, R.layout.spinner_item, methodlist);
        dt = new ArrayAdapter(mContext, R.layout.spinner_item, districtlist);
        blockName = (EditText) findViewById(R.id.bolckName);
    }

    private void initCallback(){
        btnNext.setOnClickListener(this);
        state.setOnItemSelectedListener(this);
        district.setOnItemSelectedListener(this);
    }

    private void initSpinner(){
        st.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(st);
        dt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(dt);
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
                        JSONObject att = null;
                        try {
                            att = new JSONObject(response.toString().trim());
                            String statelist = att.getString("stateList");
                            Log.d("State", statelist.toString().trim());
                            JSONArray state = new JSONArray(statelist.toString());
                            for (int i = 0; i < state.length(); i++) {
                                JSONObject statedata = state.getJSONObject(i);
                                stateDet = statedata.getString("name");
                                methodlist.add(new State(Integer.parseInt(statedata.getString("ID")), stateDet));
                                st.notifyDataSetChanged();
                            }
                            String distlist = att.getString("districtList");
                            Log.d("District", distlist.toString().trim());
                            JSONArray district = new JSONArray(distlist.toString());
                            for (int j = 0; j < district.length(); j++) {
                                JSONObject districtdata = district.getJSONObject(j);
                                districtDet = districtdata.getString("name");
                                distId = districtdata.getString("ID");
                                stateIdDis = districtdata.getString("stateID");
                                districtlist.add(new District(distId, districtDet));
                                dt.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
           /* Intent nostation = new Intent(UserSearchBus.this, Selection.class);
            startActivity(nostation);*/
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(BlockDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(BlockDetails.this);
        requestQueue.add(stringRequest1on);
    }


    @Override
    public void onClick(View v) {
        txtBlockName = blockName.getText().toString();

        dashboardData();
        Log.d("sta",txtState);
        Log.d("dis",txtDistrict);
        Log.d("block",txtBlockName);
    }

    private void dashboardData(){

        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/block/create?Name="+txtBlockName+"&StateID="+txtState+"&DistrictID="+txtDistrict,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", response.toString());


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(BlockDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json");
                return header;
            }
        };
        Log.d("URLLLLLL",stringRequest.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
