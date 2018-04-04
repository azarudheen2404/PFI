package in.co.esquareinfo.pfi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import in.co.esquareinfo.pfi.app.KeyValue;

public class PersonDetails extends AppCompatActivity {

    private Context mContext;
    private EditText name,dob,mobileNo,studyingInstName,studyDropOutYr,dropOutReason,scholarshipType;
    private Spinner gender,religion,caste,occupation,nameInVoterList,haveVoterId,maritalStatus,widowPension,seniorCitizen,oldAgePension,handicapped,handicappedPension;
    private Spinner illiterate,memberOfShg,haveSavingAcc,aadharAvalble,studyingStd,attndRehabSchool,studyingInstType,anyScholarship,dropOutClass,interestedCntStudy,awrAbtRehabPgm,atnRehabPgm,immunization;
    private String txtName,txtDob,txtMob,txtstdInst,txtStdDropOutYr,txtDropOutRsn,txtGender,txtReligion,txtCaste,txtOccupation,txtNameInVoterList,txtHaveVoterId,txtMaritalStatus,txtWidowPension,txtSeniorCitizen,txtOldAgePension,txtHandicapped,txtHandicappedPension;
    private String txtIlliterate,txtMemberOfShg,txtHaveSavingAcc,txtAadharAvalble,txtStudyingStd,txtAttndRehabSchool,txtStudyingInstType,txtAnyScholarship,txtScholarshipType,txtDropOutClass,txtInterestedCntStudy,txtAwrAbtRehabPgm,txtAtnRehabPgm,txtImmunization;
    private String genKey,genValue,relKey,relValue,casteKey,casteValue,occuKey,occuValue,nameInVoterKey,nameInVoterValue,haveVoterIdKey,haveVoterIdValue,maritalStatusKey,maritalStatusValue,widowPensionKey,widowPensionValue,seniorCitizenKey,seniorCitizenValue,oldAgePensionKey,oldAgePensionValue,handicappedKey,handicappedVlaue,handicappedPensionKey,handicappedPensionVlaue;
    private String illiterateKey,illiterateVlaue,memberOfShgKey,memberOfShgValue,haveSavingAccKey,haveSavingAccValue,aadharAvalbleKey,aadharAvalbleValue,studyingStdKey,studyingStdValue,attndRehabSchoolKey,attndRehabSchoolValue;
    private String studyingInstTypeKey,studyingInstTypeValue,anyScholarshipKey,anyScholarshipValue,dropOutClassKey,dropOutClassValue,interestedCntStudyKey,interestedCntStudyValue,awrAbtRehabPgmKey,awrAbtRehabPgmValue,atnRehabPgmKey,atnRehabPgmValue,immunizationKey,immunizationValue;
    private List<KeyValue> genList,reliList,casteList,occuList,nameInVotrList,haveVoterIdList,maritalStatusList,widowPensionList,seniorCitizenList,oldAgePensionList,handicappedList,handicappedPensionList;
    private List<KeyValue> illiterateList,memberOfShgList,haveSavingAccList,aadharAvalbleList,studyingStdList,attndRehabSchoolList,studyingInstTypeList,anyScholarshipList,dropOutClassList,interestedCntStudyList,awrAbtRehabPgmList,atnRehabPgmList,immunizationList;
    private ArrayAdapter<KeyValue> gr,rn,ce,on,nv,vi,ms,wp,sc,op,hc,hp,ie,mos,sa,av,ss,ars,st,aSc,dc,ics,awrp,atrp,imm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        initObjects();
        initCallback();
        spinnerDetails();
        initSpinner();
    }

    private void initObjects(){
        mContext = this;
        name = (EditText)findViewById(R.id.personName);
        dob = (EditText)findViewById(R.id.dob);
        mobileNo = (EditText)findViewById(R.id.mobNo);
        studyingInstName = (EditText)findViewById(R.id.instituteName);
        scholarshipType = (EditText)findViewById(R.id.scholarshipType);
        studyDropOutYr = (EditText)findViewById(R.id.dropOutYear);
        dropOutReason = (EditText)findViewById(R.id.dropOutReason);

        gender = (Spinner) findViewById(R.id.gender);
        genList = new ArrayList();
        gr = new ArrayAdapter(mContext, R.layout.spinner_item, genList);

       // ,,,,,,,,,handicappedPensionList;

        religion = (Spinner) findViewById(R.id.religion);
        reliList = new ArrayList();
        rn = new ArrayAdapter(mContext, R.layout.spinner_item, reliList);

        caste = (Spinner) findViewById(R.id.caste);
        casteList = new ArrayList();
        ce = new ArrayAdapter(mContext, R.layout.spinner_item, casteList);

        occupation = (Spinner) findViewById(R.id.occupation);
        occuList = new ArrayList();
        on = new ArrayAdapter(mContext, R.layout.spinner_item, occuList);

        nameInVoterList = (Spinner) findViewById(R.id.nameVoterList);
        nameInVotrList = new ArrayList();
        nv = new ArrayAdapter(mContext, R.layout.spinner_item, nameInVotrList);

        haveVoterId = (Spinner) findViewById(R.id.haveAVoterId);
        reliList = new ArrayList();
        rn = new ArrayAdapter(mContext, R.layout.spinner_item, reliList);

        maritalStatus = (Spinner) findViewById(R.id.maritalStatus);
        maritalStatusList = new ArrayList();
        ms = new ArrayAdapter(mContext, R.layout.spinner_item, maritalStatusList);

        widowPension = (Spinner) findViewById(R.id.getWidowPension);
        widowPensionList = new ArrayList();
        wp = new ArrayAdapter(mContext, R.layout.spinner_item, widowPensionList);

        seniorCitizen = (Spinner) findViewById(R.id.seniorCitizen);
        seniorCitizenList = new ArrayList();
        sc = new ArrayAdapter(mContext, R.layout.spinner_item, seniorCitizenList);

        oldAgePension = (Spinner) findViewById(R.id.getOldAgePension);
        oldAgePensionList = new ArrayList();
        op = new ArrayAdapter(mContext, R.layout.spinner_item, oldAgePensionList);

        handicapped = (Spinner) findViewById(R.id.handicapped);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        handicappedPension = (Spinner) findViewById(R.id.pensionForHandi);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        illiterate = (Spinner) findViewById(R.id.illiterate);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        memberOfShg = (Spinner) findViewById(R.id.memberOfshg);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        haveSavingAcc = (Spinner) findViewById(R.id.havngSA);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        aadharAvalble = (Spinner) findViewById(R.id.aadharAvailable);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        studyingStd = (Spinner) findViewById(R.id.stdyngStndrd);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        attndRehabSchool = (Spinner) findViewById(R.id.atnRehabSchl);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        studyingInstType = (Spinner) findViewById(R.id.instituteType);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        anyScholarship = (Spinner) findViewById(R.id.gettingAnyScholarship);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        dropOutClass = (Spinner) findViewById(R.id.dropOutClass);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        interestedCntStudy = (Spinner) findViewById(R.id.intrstCntnueStudy);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        awrAbtRehabPgm = (Spinner) findViewById(R.id.awareAbtRehab);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        atnRehabPgm = (Spinner) findViewById(R.id.atndAnyRehab);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);

        immunization = (Spinner) findViewById(R.id.immunization);
        handicappedList = new ArrayList();
        hc = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedList);
    }

    private void initCallback(){

    }
    private void spinnerDetails(){

    }
    private void initSpinner(){

    }
}
