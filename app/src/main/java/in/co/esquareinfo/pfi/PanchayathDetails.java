package in.co.esquareinfo.pfi;

import android.content.Context;
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
import in.co.esquareinfo.pfi.app.District;
import in.co.esquareinfo.pfi.app.HeaderCertificate;
import in.co.esquareinfo.pfi.app.State;

public class PanchayathDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Context mContext;
    private Spinner blockName;
    private EditText panchayathName;
    private String txtPanchayath;
    private int txtState, txtDistrict, txtBlock;
    private String blockId, stateId, districtId, blckName;
    private ImageView btnNext;
    private List<Block> blockList;
    private ArrayAdapter<Block> bt;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panchayath_details);

        HeaderCertificate myHeaderClass = new HeaderCertificate(this);
        myHeaderClass.handleSSLHandshake();
        initObjects();
        initCallback();
        blockDetails();
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent == blockName) {
            txtDistrict = Integer.parseInt((blockList.get(blockName.getSelectedItemPosition())).getDistrictId());
            txtState = Integer.parseInt((blockList.get(blockName.getSelectedItemPosition())).getStateId());
            txtBlock = Integer.parseInt((blockList.get(blockName.getSelectedItemPosition())).getBlockId());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initObjects(){
        mContext = this;

        blockName = (Spinner) findViewById(R.id.blockName);
        btnNext = (ImageView) findViewById(R.id.next);
        blockList = new ArrayList<>();
        bt = new ArrayAdapter<Block>(mContext,R.layout.spinner_item, blockList);
        panchayathName = (EditText) findViewById(R.id.panchayathName);
        jsonObject = new JSONObject();
    }

    private void initCallback(){
        btnNext.setOnClickListener(this);
        blockName.setOnItemSelectedListener(this);
    }

    private void initSpinner(){
        bt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockName.setAdapter(bt);
    }


    @Override
    public void onClick(View v) {
        txtPanchayath = panchayathName.getText().toString();
//        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", txtPanchayath);
            jsonObject.put("districtID", txtDistrict);
            jsonObject.put("stateID", txtState);
            jsonObject.put("blockID", txtBlock);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON",jsonObject.toString());
       // dashboardData();
        /*Log.d("sta",txtState);
        Log.d("dis",txtDistrict);
        Log.d("block",txtBlockName);*/
    }

    private void blockDetails(){
        StringRequest stringRequest1on;
        stringRequest1on = new StringRequest(Request.Method.GET, "https://schp.popularfrontindia.org/vdpQA/services/com/block/getAll",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("output",response.toString());

                        JSONObject att = null;
                        try {
                            att = new JSONObject(response.toString().trim());
                            String statelist = att.getString("blockList");
                           // Log.d("State", statelist.toString().trim());
                            JSONArray state = new JSONArray(statelist.toString());
                            for (int i = 0; i < state.length(); i++) {
                                JSONObject statedata = state.getJSONObject(i);
                                blockId = statedata.getString("ID");
                                blckName = statedata.getString("name");
                                districtId = statedata.getString("districtID");
                                stateId = statedata.getString("stateID");
                                blockList.add(new Block(blockId, blckName, stateId, districtId));
                                bt.notifyDataSetChanged();
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
                            Toast.makeText(PanchayathDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        stringRequest1on.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(PanchayathDetails.this);
        requestQueue.add(stringRequest1on);
    }

    private void dashboardData(){

        String url = "https://schp.popularfrontindia.org/vdpQA/services/com/panchayath/create";
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
                            Toast.makeText(PanchayathDetails.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                        Log.d("Error",error.toString());
                    }
                });
        Log.d("URLLLLLL",stringRequest.toString());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
