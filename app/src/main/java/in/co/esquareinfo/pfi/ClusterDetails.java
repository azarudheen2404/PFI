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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.co.esquareinfo.pfi.app.Block;
import in.co.esquareinfo.pfi.app.Cluster;
import in.co.esquareinfo.pfi.app.District;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.Panchayath;
import in.co.esquareinfo.pfi.app.State;

public class ClusterDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Context mContext;
    private Spinner state, district;
    private EditText clusterName;
    private String txtCluster;
    private int txtState, txtDistrict;
    private ImageView btnNext;
    private List<District> districtlist;
    private ArrayAdapter<District> dt;
    private List<State> methodlist;
    private JSONObject jsonObject;
    private ArrayAdapter<State> st;
    private String stateDet, districtDet, distId, stateIdDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_details);
        getSupportActionBar().setTitle("Create Cluster");

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
            txtState = (methodlist.get(state.getSelectedItemPosition()).getId());
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
                    int stId = Integer.parseInt(stateIdDis);
                    if (stId == txtState) {
                        Log.d("Districttttttt", districtDet);
                        districtlist.add(new District(distId, districtDet));
                    }
                    dt.notifyDataSetChanged();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (parent == district) {
            txtDistrict = Integer.parseInt((districtlist.get(district.getSelectedItemPosition())).getDisId());
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
        clusterName = (EditText) findViewById(R.id.clusterName);
        jsonObject = new JSONObject();
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


    @Override
    public void onClick(View v) {
        txtCluster = clusterName.getText().toString();
//        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", txtCluster);
            jsonObject.put("districtID", txtDistrict);
            jsonObject.put("stateID", txtState);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (clusterName.getText().toString().length() == 0){
            Toast.makeText(mContext, "Please fill the column", Toast.LENGTH_SHORT).show();
        }else {
            dashboardData();

        }
    }

    private void stdtDetails(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
        String stdt =pref.getString("StDt",null);

        JSONObject att = null;
        try {
            att = new JSONObject(stdt.toString().trim());
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

        }

    }

    private void dashboardData(){

        String url = "https://schp.popularfrontindia.org/vdpQA/services/com/cluster/create";
        Log.d("JSON",jsonObject.toString());
        JsonObjectRequest stringRequest;
        stringRequest = new JsonObjectRequest(Request.Method.PUT, url ,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                        Toast.makeText(mContext, "Cluster Created", Toast.LENGTH_SHORT).show();
                        Intent next = new Intent(mContext,VillageDetails.class);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(ClusterDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        Log.d("URLLLLLL",stringRequest.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
