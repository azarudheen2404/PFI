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
    private String ranName,ranId,keyYN,valueYN,waterName,waterKey,toiletKey,toiletValue,healthKey,healthValue,ashaWrkKey,ashaWrkValue,foodKey,foodValue,houseKey,houseValue;
    private List<KeyValue> rationList,ynList,waterList,toiletList,healthList,ashaWorkList,foodList,houseList;
    private ArrayAdapter<KeyValue> rc,yn,wt,tt,hh,aw,fd,he;
    private String houseOwnKey,houseOwnValue,cookKey,cookValue,landKey,landValue,rehabKey,rehabValue,childSchlKey,childSchlValue,freeRemKey,freeRemValue,govtScmsKey,govtSchmsValue,attnEduKey,attnEduValue;
    private List<KeyValue> houseownList,cookList,landList,rehabList,childSchlList,freeRemList,govtSchmsList,attnEduList;
    private ArrayAdapter<KeyValue>ho,ck,ld,rb,cs,fr,gs,ae;

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
        }else if (parent == drinkingWater) {

            txtDrinkingWater = waterList.get(drinkingWater.getSelectedItemPosition()).getRationId();

        }else if (parent == toilet) {

            txtToilet = toiletList.get(toilet.getSelectedItemPosition()).getRationId();

        }else if (parent == electricity) {

            txtElectricity = ynList.get(electricity.getSelectedItemPosition()).getRationId();
            Log.d("electricity", String.valueOf(txtElectricity));
        }else if (parent == electricity) {

            txtElectricity = ynList.get(electricity.getSelectedItemPosition()).getRationId();
            Log.d("electricity", String.valueOf(txtElectricity));
        }else if (parent == electricity) {

            txtElectricity = ynList.get(electricity.getSelectedItemPosition()).getRationId();
            Log.d("electricity", String.valueOf(txtElectricity));
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
        toiletList = new ArrayList();
        tt = new ArrayAdapter(mContext, R.layout.spinner_item, toiletList);

        healthCare = (Spinner) findViewById(R.id.healthCare);
        healthList = new ArrayList();
        hh = new ArrayAdapter(mContext, R.layout.spinner_item, healthList);

        ashaWorker = (Spinner) findViewById(R.id.ashaWorkerComing);
        ashaWorkList = new ArrayList();
        aw = new ArrayAdapter(mContext, R.layout.spinner_item, ashaWorkList);

        foodFromAnganwadi = (Spinner) findViewById(R.id.foodFrmAnganwadi);
        foodList = new ArrayList();
        fd = new ArrayAdapter(mContext, R.layout.spinner_item, foodList);

        houseType = (Spinner) findViewById(R.id.houseType);
        houseList = new ArrayList();
        he = new ArrayAdapter(mContext, R.layout.spinner_item, houseList);

        houseOwnership = (Spinner) findViewById(R.id.houseOwrnership);
        houseownList = new ArrayList();
        ho = new ArrayAdapter(mContext, R.layout.spinner_item, houseownList);

        cookingBy = (Spinner) findViewById(R.id.cookingBy);
        cookList = new ArrayList();
        ck = new ArrayAdapter(mContext, R.layout.spinner_item, cookList);

        landOwned = (Spinner) findViewById(R.id.landOwned);
        landList = new ArrayList();
        ld = new ArrayAdapter(mContext, R.layout.spinner_item, landList);

        rehabBenefit = (Spinner) findViewById(R.id.rehabBeneficial);
        rehabList = new ArrayList();
        rb = new ArrayAdapter(mContext, R.layout.spinner_item, rehabList);

        childStartedSchool = (Spinner) findViewById(R.id.childStartedSchool);
        childSchlList = new ArrayList();
        cs = new ArrayAdapter(mContext, R.layout.spinner_item, childSchlList);

        freeRemedialCoaching = (Spinner) findViewById(R.id.gotRemedialCoach);
        freeRemList = new ArrayList();
        fr = new ArrayAdapter(mContext, R.layout.spinner_item, freeRemList);

        awarenessAbtGovtSchms = (Spinner) findViewById(R.id.freeMedicalCare);
        govtSchmsList = new ArrayList();
        gs = new ArrayAdapter(mContext, R.layout.spinner_item, govtSchmsList);

        moreAttnChildEdu = (Spinner) findViewById(R.id.atnOnChildEdu);
        attnEduList = new ArrayList();
        ae = new ArrayAdapter(mContext, R.layout.spinner_item, attnEduList);
    }

    private void initCallback(){

        villageName.setOnItemSelectedListener(this);
        rationCard.setOnItemSelectedListener(this);
        electricity.setOnItemSelectedListener(this);
        drinkingWater.setOnItemSelectedListener(this);
        toilet.setOnItemSelectedListener(this);
        healthCare.setOnItemSelectedListener(this);
        ashaWorker.setOnItemSelectedListener(this);
        foodFromAnganwadi.setOnItemSelectedListener(this);
        houseType.setOnItemSelectedListener(this);

    }

    private void initSpinner(){
        vl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        villageName.setAdapter(vl);

        rc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rationCard.setAdapter(rc);

        yn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        electricity.setAdapter(yn);

        wt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinkingWater.setAdapter(wt);

        tt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toilet.setAdapter(tt);

        hh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        healthCare.setAdapter(hh);

        aw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ashaWorker.setAdapter(aw);

        fd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodFromAnganwadi.setAdapter(fd);

        he.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseType.setAdapter(he);
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
                JSONObject rationdata = ration.getJSONObject(i);
                ranId = rationdata.getString("key");
                ranName = rationdata.getString("value");
                rationList.add(new KeyValue(ranId, ranName));
                rc.notifyDataSetChanged();
            }

            String yNList = spinnerData.getString("YN");
            // Log.d("State", statelist.toString().trim());
            JSONArray yN = new JSONArray(yNList.toString());
            for (int i = 0; i < yN.length(); i++) {
                JSONObject electricitydata = yN.getJSONObject(i);
                keyYN = electricitydata.getString("key");
                valueYN = electricitydata.getString("value");
                ynList.add(new KeyValue(keyYN, valueYN));
                yn.notifyDataSetChanged();
            }

            String wtList = spinnerData.getString("waterSourceType");
            // Log.d("State", statelist.toString().trim());
            JSONArray water = new JSONArray(wtList.toString());
            for (int i = 0; i < water.length(); i++) {
                JSONObject waterdata = water.getJSONObject(i);
                waterKey = waterdata.getString("key");
                waterName = waterdata.getString("value");
                waterList.add(new KeyValue(waterKey, waterName));
                wt.notifyDataSetChanged();
            }

            String ttList = spinnerData.getString("toiletType");
            // Log.d("State", statelist.toString().trim());
            JSONArray toilt = new JSONArray(ttList.toString());
            for (int i = 0; i < toilt.length(); i++) {
                JSONObject toiletdata = toilt.getJSONObject(i);
                toiletKey = toiletdata.getString("key");
                toiletValue = toiletdata.getString("value");
                toiletList.add(new KeyValue(toiletKey, toiletValue));
                tt.notifyDataSetChanged();
            }

            String hhList = spinnerData.getString("healthCareType");
            // Log.d("State", statelist.toString().trim());
            JSONArray healcre = new JSONArray(hhList.toString());
            for (int i = 0; i < healcre.length(); i++) {
                JSONObject healthdata = healcre.getJSONObject(i);
                healthKey = healthdata.getString("key");
                healthValue = healthdata.getString("value");
                healthList.add(new KeyValue(healthKey, healthValue));
                hh.notifyDataSetChanged();
            }

            String awList = spinnerData.getString("YNXZ");
            // Log.d("State", statelist.toString().trim());
            JSONArray ashwrker = new JSONArray(awList.toString());
            for (int i = 0; i < ashwrker.length(); i++) {
                JSONObject ashadata = ashwrker.getJSONObject(i);
                ashaWrkKey = ashadata.getString("key");
                ashaWrkValue = ashadata.getString("value");
                ashaWorkList.add(new KeyValue(ashaWrkKey, ashaWrkValue));
                aw.notifyDataSetChanged();
            }

            String fdList = spinnerData.getString("YNX");
            // Log.d("State", statelist.toString().trim());
            JSONArray foodfrmanwdi = new JSONArray(fdList.toString());
            for (int i = 0; i < foodfrmanwdi.length(); i++) {
                JSONObject fooddata = foodfrmanwdi.getJSONObject(i);
                foodKey = fooddata.getString("key");
                foodValue = fooddata.getString("value");
                foodList.add(new KeyValue(foodKey, foodValue));
                fd.notifyDataSetChanged();
            }

            String heList = spinnerData.getString("houseBuildingType");
            // Log.d("State", statelist.toString().trim());
            JSONArray housetype = new JSONArray(heList.toString());
            for (int i = 0; i < housetype.length(); i++) {
                JSONObject housedata = housetype.getJSONObject(i);
                houseKey = housedata.getString("key");
                houseValue = housedata.getString("value");
                houseList.add(new KeyValue(houseKey, houseValue));
                he.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
