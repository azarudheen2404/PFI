package in.co.esquareinfo.pfi;

import android.content.Context;
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

import in.co.esquareinfo.pfi.app.Cluster;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.Panchayath;

public class VillageDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Context mContext;
    private Spinner clusterName;
    private EditText villageName;
    private String txtVillage;
    private int txtState, txtDistrict, txtBlock, txtPanchayath, txtCluster;
    private String blockId, stateId, districtId, blckId, panchId,clstrId, clstrName;
    private ImageView btnNext;
    private List<Cluster> clusterList;
    private ArrayAdapter<Cluster> ct;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village_details);
        getSupportActionBar().setTitle("Create Village");

        HeaderCertificate myHeaderClass = new HeaderCertificate(this);
        myHeaderClass.handleSSLHandshake();
        initObjects();
        initCallback();
        blockDetails();
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/*
        if (parent == panchayathName) {
            txtDistrict = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getDistrictId());
            txtState = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getStateId());
            txtBlock = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getBlockId());
            txtPanchayath = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getPanchId());
        }*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initObjects(){
        mContext = this;

        clusterName = (Spinner) findViewById(R.id.clusterName);
        btnNext = (ImageView) findViewById(R.id.next);
        clusterList = new ArrayList<>();
        ct = new ArrayAdapter<Cluster>(mContext,R.layout.spinner_item, clusterList);
        villageName = (EditText) findViewById(R.id.villageName);
        jsonObject = new JSONObject();
    }

    private void initCallback(){
        btnNext.setOnClickListener(this);
        clusterName.setOnItemSelectedListener(this);
    }

    private void initSpinner(){
        ct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clusterName.setAdapter(ct);
    }


    @Override
    public void onClick(View v) {
        txtVillage = villageName.getText().toString();
//        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", txtVillage);
            jsonObject.put("districtID", txtDistrict);
            jsonObject.put("stateID", txtState);
            jsonObject.put("blockID", txtBlock);
            jsonObject.put("panchayathID",txtPanchayath);
            jsonObject.put("clusterID",txtCluster);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (villageName.getText().toString().length() == 0){
            Toast.makeText(mContext, "Please fill the column", Toast.LENGTH_SHORT).show();
        }else {
            dashboardData();
        }
     //   Log.d("JSON",jsonObject.toString());
        // dashboardData();
        /*Log.d("sta",txtState);
        Log.d("dis",txtDistrict);
        Log.d("block",txtBlockName);*/
    }

    private void blockDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/cluster/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());

                       /* JSONObject att = null;
                        try {
                            att = new JSONObject(response.toString().trim());
                            String statelist = att.getString("panchayathList");
                            // Log.d("State", statelist.toString().trim());
                            JSONArray state = new JSONArray(statelist.toString());
                            for (int i = 0; i < state.length(); i++) {
                                JSONObject statedata = state.getJSONObject(i);
                                panchId = statedata.getString("ID");
                                panchName = statedata.getString("name");
                                districtId = statedata.getString("districtID");
                                stateId = statedata.getString("stateID");
                                blockId = statedata.getString("blockID");
                                panchList.add(new Panchayath(panchId, panchName, stateId, districtId, blockId));
                                pt.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }*/
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(VillageDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(VillageDetails.this);
        requestQueue.add(stringRequest1on);
    }

    private void dashboardData(){

        String url = "https://schp.popularfrontindia.org/vdpQA/services/com/village/create";
        Log.d("JSON",jsonObject.toString());
        JsonObjectRequest stringRequest;
        stringRequest = new JsonObjectRequest(Request.Method.PUT, url ,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(VillageDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        Log.d("URLLLLLL",stringRequest.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
