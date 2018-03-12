package in.co.esquareinfo.pfi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Survey extends AppCompatActivity {

    private EditText familyName,address,avgMonIncome,avgMonExpense,noVehicles,typeVehicles;
    private Spinner villageName,rationCard,electricity,drinkingWater,toilet,healthCare,ashaWorker,foodFromAnganwadi,houseType;
    private Spinner houseOwnership,cookingBy,landOwned,rehabBenefit,childStartedSchool,freeRemedialCoaching,awarenessAbtGovtSchms,moreAttnChildEdu;
    private String txtFamilyName,txtAddress,txtAvgMonIncome,txtAvgMonExpense,txtNoVehicles,txtTypeVehicles;
    private String TxtVillageName,txtRationCard,txtElectricity,txtDrinkingWater,txtToilet,txtHealthCare,txtAshaWorker,txtFoodFromAnganwadi,txtHouseType;
    private String txtHouseOwnership,txtCookingBy,txtLandOwned,txtRehabBenefit,txtChildStartedSchool,txtFreeRemedialCoaching,txtAwarenessAbtGovtSchms,txtMoreAttnChildEdu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        initObjects();
    }

    private void initObjects(){

        familyName = (EditText)findViewById(R.id.familyName);
        address = (EditText)findViewById(R.id.address);
        avgMonIncome = (EditText)findViewById(R.id.avgMnIncome);
        avgMonExpense = (EditText)findViewById(R.id.avgMnExpense);
        noVehicles = (EditText)findViewById(R.id.noOfVehicles);
        typeVehicles = (EditText)findViewById(R.id.typeOfVehicle);

        villageName = (Spinner) findViewById(R.id.villageNameSur);
        rationCard = (Spinner) findViewById(R.id.rationCard);
        electricity = (Spinner) findViewById(R.id.electricity);
        drinkingWater = (Spinner) findViewById(R.id.drnkWtrSrc);
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

       /* consent = (Spinner)findViewById(R.id.consent);
        consent.setOnItemSelectedListener(this);
        List<String> consentlist = new ArrayList<String>();
        consentlist.add("SELECT");
        consentlist.add("Not received");
        consentlist.add("Received");
        //Creating the ArrayAdapter instance having the consent list
        ArrayAdapter statelist = new ArrayAdapter(Survey.this,R.layout.support_simple_spinner_dropdown_item,consentlist);
        statelist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        consent.setAdapter(statelist);*/
    }
}
