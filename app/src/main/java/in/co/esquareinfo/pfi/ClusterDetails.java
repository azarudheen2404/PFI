package in.co.esquareinfo.pfi;

import android.content.Context;
import android.content.Intent;
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
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.Panchayath;

public class ClusterDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Context mContext;
    private Spinner panchayathName;
    private EditText clusterName;
    private String txtCluster;
    private int txtState, txtDistrict, txtBlock, txtPanchayath;
    private String blockId, stateId, districtId, blckId, panchId,panchName;
    private ImageView btnNext;
    private List<Panchayath> panchList;
    private ArrayAdapter<Panchayath> pt;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster_details);

        HeaderCertificate myHeaderClass = new HeaderCertificate(this);
        myHeaderClass.handleSSLHandshake();
        initObjects();
        initCallback();
        blockDetails();
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent == panchayathName) {
            txtDistrict = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getDistrictId());
            txtState = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getStateId());
            txtBlock = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getBlockId());
            txtPanchayath = Integer.parseInt((panchList.get(panchayathName.getSelectedItemPosition())).getPanchId());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initObjects(){
        mContext = this;

        panchayathName = (Spinner) findViewById(R.id.panchayathName);
        btnNext = (ImageView) findViewById(R.id.next);
        panchList = new ArrayList<>();
        pt = new ArrayAdapter<Panchayath>(mContext,R.layout.spinner_item, panchList);
        clusterName = (EditText) findViewById(R.id.clusterName);
        jsonObject = new JSONObject();
    }

    private void initCallback(){
        btnNext.setOnClickListener(this);
        panchayathName.setOnItemSelectedListener(this);
    }

    private void initSpinner(){
        pt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        panchayathName.setAdapter(pt);
    }


    @Override
    public void onClick(View v) {
        txtCluster = clusterName.getText().toString();
//        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", txtCluster);
            jsonObject.put("districtID", txtDistrict);
            jsonObject.put("stateID", txtState);
            jsonObject.put("blockID", txtBlock);
            jsonObject.put("panchayathID",txtPanchayath);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (clusterName.getText().toString().length() == 0){
            Toast.makeText(mContext, "Please fill the column", Toast.LENGTH_SHORT).show();
        }else {
            dashboardData();
        }
        // dashboardData();
        /*Log.d("sta",txtState);
        Log.d("dis",txtDistrict);
        Log.d("block",txtBlockName);*/
    }

    private void blockDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/panchayath/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());

                        JSONObject att = null;
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

                        }
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
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(ClusterDetails.this);
        requestQueue.add(stringRequest1on);
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
                        startActivity(next);


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
