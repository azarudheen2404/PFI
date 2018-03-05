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

import in.co.esquareinfo.pfi.app.Block;
import in.co.esquareinfo.pfi.app.Cluster;
import in.co.esquareinfo.pfi.app.District;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.Panchayath;
import in.co.esquareinfo.pfi.app.State;

public class VillageDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Context mContext;
    private Spinner blockName;
    private Spinner state, district;
    private Spinner panchayathName;
    private EditText villageName;
    private String txtVillage;
    private int txtState, txtDistrict, txtBlock, txtPanchayath;
    private String blockId, stateId, districtId, blckId, panchId;
    private ImageView btnNext;
    private List<District> districtlist;
    private ArrayAdapter<District> dt;
    private List<State> methodlist;
    private ArrayAdapter<State> st;
    private List<Block> blockList;
    private ArrayAdapter<Block> bt;
    private List<Panchayath> panchayathList;
    private ArrayAdapter<Panchayath> pt;
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
       // blockDetails();
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
        state = (Spinner) findViewById(R.id.state);
        district = (Spinner) findViewById(R.id.district);
        btnNext = (ImageView) findViewById(R.id.next);
        methodlist = new ArrayList();
        districtlist = new ArrayList();
        st = new ArrayAdapter(mContext, R.layout.spinner_item, methodlist);
        dt = new ArrayAdapter(mContext, R.layout.spinner_item, districtlist);

        panchayathName = (Spinner) findViewById(R.id.panchayathName);
        btnNext = (ImageView) findViewById(R.id.next);
        blockList = new ArrayList<>();
        bt = new ArrayAdapter<Block>(mContext,R.layout.spinner_item, blockList);
        panchayathList = new ArrayList<>();
        pt = new ArrayAdapter<Panchayath>(mContext,R.layout.spinner_item, panchayathList);
        villageName = (EditText) findViewById(R.id.villageName);
        jsonObject = new JSONObject();
    }

    private void initCallback(){
        btnNext.setOnClickListener(this);
        state.setOnItemSelectedListener(this);
        district.setOnItemSelectedListener(this);
        blockName.setOnItemSelectedListener(this);
        panchayathName.setOnItemSelectedListener(this);
    }

    private void initSpinner(){
        st.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(st);
        dt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(dt);
        bt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockName.setAdapter(bt);
        pt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        panchayathName.setAdapter(pt);
    }


    @Override
    public void onClick(View v) {
        txtVillage = villageName.getText().toString();
//        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", txtVillage);
            jsonObject.put("districtID", txtDistrict);
            jsonObject.put("stateID", txtState);

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

  /*  private void blockDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/cluster/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());

                        JSONObject att = null;
                        try {
                            att = new JSONObject(response.toString().trim());
                            String statelist = att.getString("clusterList");
                            // Log.d("State", statelist.toString().trim());
                            JSONArray state = new JSONArray(statelist.toString());
                            for (int i = 0; i < state.length(); i++) {
                                JSONObject statedata = state.getJSONObject(i);
                                clstrId = statedata.getString("ID");
                                clstrName = statedata.getString("name");
                                districtId = statedata.getString("districtID");
                                stateId = statedata.getString("stateID");
                                clusterList.add(new Cluster(clstrId, clstrName, stateId, districtId));
                                ct.notifyDataSetChanged();
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
    }*/

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
