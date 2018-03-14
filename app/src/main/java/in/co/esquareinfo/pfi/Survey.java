package in.co.esquareinfo.pfi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.co.esquareinfo.pfi.app.KeyValue;
import in.co.esquareinfo.pfi.app.Village;

public class Survey extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context mContext;
    private EditText familyName,address,avgMonIncome,avgMonExpense,noVehicles,typeVehicles;
    private Spinner villageName,rationCard,electricity,drinkingWater,toilet,healthCare,ashaWorker,foodFromAnganwadi,houseType;
    private Spinner houseOwnership,cookingBy,landOwned,rehabBenefit,childStartedSchool,freeRemedialCoaching,awarenessAbtGovtSchms,moreAttnChildEdu;
    private Button save;
    private String txtFamilyName,txtAddress,txtAvgMonIncome,txtAvgMonExpense,txtNoVehicles,txtTypeVehicles;
    private int txtVillageName;
    private String txtRationCard,txtElectricity,txtDrinkingWater,txtToilet,txtHealthCare,txtAshaWorker,txtFoodFromAnganwadi,txtHouseType;
    private String txtHouseOwnership,txtCookingBy,txtLandOwned,txtRehabBenefit,txtChildStartedSchool,txtFreeRemedialCoaching,txtAwarenessAbtGovtSchms,txtMoreAttnChildEdu;
    private String villName,villId;
    private List<Village> villageList;
    private ArrayAdapter<Village> vl;
    private String ranName,ranId,keyYN,valueYN,waterName,waterKey;
    private List<KeyValue> rationList,ynList,waterList;
    private ArrayAdapter<KeyValue> rc,yn,wt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        initObjects();
        initCallback();
        villageDetails();
        spinnerDetails();
        initSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent == villageName) {

            txtVillageName = Integer.parseInt((villageList.get(villageName.getSelectedItemPosition())).getVillageId());
            Log.d("blockkkk", String.valueOf(txtVillageName));
        }else if (parent == rationCard) {

            txtRationCard = rationList.get(rationCard.getSelectedItemPosition()).getRationId();
            Log.d("ration", String.valueOf(txtRationCard));
        }else if (parent == electricity) {

            txtElectricity = ynList.get(electricity.getSelectedItemPosition()).getRationId();
            Log.d("electricity", String.valueOf(txtElectricity));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initObjects(){
        mContext = this;
        familyName = (EditText)findViewById(R.id.familyName);
        address = (EditText)findViewById(R.id.address);
        avgMonIncome = (EditText)findViewById(R.id.avgMnIncome);
        avgMonExpense = (EditText)findViewById(R.id.avgMnExpense);
        noVehicles = (EditText)findViewById(R.id.noOfVehicles);
        typeVehicles = (EditText)findViewById(R.id.typeOfVehicle);

        villageName = (Spinner) findViewById(R.id.villageNameSur);
        villageList = new ArrayList();
        vl = new ArrayAdapter(mContext, R.layout.spinner_item, villageList);

        rationCard = (Spinner) findViewById(R.id.rationCard);
        rationList = new ArrayList();
        rc = new ArrayAdapter(mContext, R.layout.spinner_item, rationList);

        electricity = (Spinner) findViewById(R.id.electricity);
        ynList = new ArrayList();
        yn = new ArrayAdapter(mContext, R.layout.spinner_item, ynList);

        drinkingWater = (Spinner) findViewById(R.id.drnkWtrSrc);
        waterList = new ArrayList();
        wt = new ArrayAdapter(mContext, R.layout.spinner_item, waterList);

        toilet = (Spinner) findViewById(R.id.toilet);
        healthCare = (Spinner) findViewById(R.id.healthCare);
        ashaWorker = (Spinner) findViewById(R.id.ashaWorkerComing);
        foodFromAnganwadi = (Spinner) findViewById(R.id.foodFrmAnganwadi);
        houseType = (Spinner) findViewById(R.id.houseType);
        houseOwnership = (Spinner) findViewById(R.id.houseOwrnership);
        cookingBy = (Spinner) findViewById(R.id.cookingBy);
        landOwned = (Spinner) findViewById(R.id.landOwned);
        rehabBenefit = (Spinner) findViewById(R.id.rehabBeneficial);
        childStartedSchool = (Spinner) findViewById(R.id.childStartedSchool);
        freeRemedialCoaching = (Spinner) findViewById(R.id.gotRemedialCoach);
        awarenessAbtGovtSchms = (Spinner) findViewById(R.id.freeMedicalCare);
        moreAttnChildEdu = (Spinner) findViewById(R.id.atnOnChildEdu);

    }

    private void initCallback(){

        villageName.setOnItemSelectedListener(this);
        rationCard.setOnItemSelectedListener(this);
        electricity.setOnItemSelectedListener(this);

    }

    private void initSpinner(){
        vl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        villageName.setAdapter(vl);

        rc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rationCard.setAdapter(rc);

        yn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        electricity.setAdapter(yn);
    }

    private void villageDetails(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
        String stdt =pref.getString("village",null);
                        JSONObject att = null;
                        try {
                            att = new JSONObject(stdt.toString().trim());
                            String statelist = att.getString("blockList");
                            // Log.d("State", statelist.toString().trim());
                            JSONArray state = new JSONArray(statelist.toString());
                            for (int i = 0; i < state.length(); i++) {
                                JSONObject statedata = state.getJSONObject(i);
                                villId = statedata.getString("ID");
                                villName = statedata.getString("name");
                                villageList.add(new Village(villId, villName));
                                vl.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
    }

    private void spinnerDetails(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
        String stdt =pref.getString("StDt",null);
        JSONObject spinnerData = null;
        try {
            spinnerData = new JSONObject(stdt.toString().trim());
            String rationCardList = spinnerData.getString("rationCardType");
            // Log.d("State", statelist.toString().trim());
            JSONArray ration = new JSONArray(rationCardList.toString());
            for (int i = 0; i < ration.length(); i++) {
                JSONObject statedata = ration.getJSONObject(i);
                ranId = statedata.getString("key");
                ranName = statedata.getString("value");
                rationList.add(new KeyValue(ranId, ranName));
                rc.notifyDataSetChanged();
            }

            String yNList = spinnerData.getString("YN");
            // Log.d("State", statelist.toString().trim());
            JSONArray yN = new JSONArray(yNList.toString());
            for (int i = 0; i < yN.length(); i++) {
                JSONObject statedata = yN.getJSONObject(i);
                keyYN = statedata.getString("key");
                valueYN = statedata.getString("value");
                ynList.add(new KeyValue(keyYN, valueYN));
                yn.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
