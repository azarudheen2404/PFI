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
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.co.esquareinfo.pfi.app.KeyValue;

public class PersonDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context mContext;
    private EditText name,dob,mobileNo,studyingInstName,studyDropOutYr,dropOutReason,scholarshipType;
    private Spinner gender,religion,caste,occupation,nameInVoterList,haveVoterId,maritalStatus,widowPension,seniorCitizen,oldAgePension,handicapped,handicappedPension;
    private Spinner illiterate,memberOfShg,haveSavingAcc,aadharAvalble,studyingStd,attndRehabSchool,studyingInstType,anyScholarship,dropOutClass,interestedCntStudy,awrAbtRehabPgm,atnRehabPgm,immunization;
    private String txtName,txtDob,txtMob,txtstdInst,txtStdDropOutYr,txtDropOutRsn,txtGender,txtReligion,txtCaste,txtOccupation,txtNameInVoterList,txtHaveVoterId,txtMaritalStatus,txtWidowPension,txtSeniorCitizen,txtOldAgePension,txtHandicapped,txtHandicappedPension;
    private String txtIlliterate,txtMemberOfShg,txtHaveSavingAcc,txtAadharAvalble,txtStudyingStd,txtAttndRehabSchool,txtStudyingInstType,txtAnyScholarship,txtScholarshipType,txtDropOutClass,txtInterestedCntStudy,txtAwrAbtRehabPgm,txtAtnRehabPgm,txtImmunization;
    private String genKey,genValue,relKey,relValue,casteKey,casteValue,occuKey,occuValue,ynzKey,ynzValue,ynKey,ynValue,nameInVoterKey,nameInVoterValue,haveVoterIdKey,haveVoterIdValue,maritalStatusKey,maritalStatusValue,widowPensionKey,widowPensionValue,seniorCitizenKey,seniorCitizenValue,oldAgePensionKey,oldAgePensionValue,handicappedKey,handicappedVlaue,handicappedPensionKey,handicappedPensionVlaue;
    private String illiterateKey,illiterateVlaue,memberOfShgKey,memberOfShgValue,haveSavingAccKey,haveSavingAccValue,aadharAvalbleKey,aadharAvalbleValue,studyingStdKey,studyingStdValue,attndRehabSchoolKey,attndRehabSchoolValue;
    private String studyingInstTypeKey,studyingInstTypeValue,anyScholarshipKey,anyScholarshipValue,dropOutClassKey,dropOutClassValue,interestedCntStudyKey,interestedCntStudyValue,awrAbtRehabPgmKey,awrAbtRehabPgmValue,atnRehabPgmKey,atnRehabPgmValue,immunizationKey,immunizationValue;
    private List<KeyValue> genList,reliList,casteList,occuList,nameInVotrList,haveVoterIdList,maritalStatusList,widowPensionList,seniorCitizenList,oldAgePensionList,handicappedList,handicappedPensionList;
    private List<KeyValue> illiterateList,memberOfShgList,haveSavingAccList,aadharAvalbleList,studyingStdList,attndRehabSchoolList,studyingInstTypeList,anyScholarshipList,dropOutClassList,interestedCntStudyList,awrAbtRehabPgmList,atnRehabPgmList,immunizationList;
    private ArrayAdapter<KeyValue> gr,rn,ce,on,nv,vi,ms,wp,sc,op,hc,hp,ie,mos,sa,aav,ss,ars,st,aSc,dc,ics,awrp,atrp,imm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        initObjects();
        initCallback();
        spinnerDetails();
        initSpinner();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent == gender) {

            txtGender = (genList.get(gender.getSelectedItemPosition())).getRationId();

        }else if (parent == religion) {

            txtReligion = reliList.get(religion.getSelectedItemPosition()).getRationId();
        }else if (parent == caste) {

            txtCaste = casteList.get(caste.getSelectedItemPosition()).getRationId();

        }else if (parent == occupation) {

            txtOccupation = occuList.get(occupation.getSelectedItemPosition()).getRationId();

        }else if (parent == nameInVoterList) {

            txtNameInVoterList = nameInVotrList.get(nameInVoterList.getSelectedItemPosition()).getRationId();

        }else if (parent == haveVoterId) {

            txtHaveVoterId = haveVoterIdList.get(haveVoterId.getSelectedItemPosition()).getRationId();

        }else if (parent == maritalStatus) {

            txtMaritalStatus = maritalStatusList.get(maritalStatus.getSelectedItemPosition()).getRationId();

        }else if (parent == widowPension) {

            txtWidowPension = widowPensionList.get(widowPension.getSelectedItemPosition()).getRationId();

        }else if (parent == seniorCitizen) {

            txtSeniorCitizen = seniorCitizenList.get(seniorCitizen.getSelectedItemPosition()).getRationId();

        }else if (parent == oldAgePension) {

            txtOldAgePension = oldAgePensionList.get(oldAgePension.getSelectedItemPosition()).getRationId();

        }else if (parent == handicapped) {

            txtHandicapped = handicappedList.get(handicapped.getSelectedItemPosition()).getRationId();

        }else if (parent == handicappedPension) {

            txtHandicappedPension = handicappedList.get(handicappedPension.getSelectedItemPosition()).getRationId();

        }else if (parent == illiterate) {

            txtIlliterate = illiterateList.get(illiterate.getSelectedItemPosition()).getRationId();

        }else if (parent == memberOfShg) {

            txtMemberOfShg = memberOfShgList.get(memberOfShg.getSelectedItemPosition()).getRationId();

        }else if (parent == haveSavingAcc) {

            txtHaveSavingAcc = haveSavingAccList.get(haveSavingAcc.getSelectedItemPosition()).getRationId();

        }else if (parent == aadharAvalble) {

            txtAadharAvalble = aadharAvalbleList.get(aadharAvalble.getSelectedItemPosition()).getRationId();

        }else if (parent == studyingStd) {

            txtStudyingStd = studyingStdList.get(studyingStd.getSelectedItemPosition()).getRationId();
        }else if (parent == attndRehabSchool) {

            txtAttndRehabSchool = attndRehabSchoolList.get(attndRehabSchool.getSelectedItemPosition()).getRationId();

        }else if (parent == studyingInstType) {

            txtStudyingInstType = studyingInstTypeList.get(studyingInstType.getSelectedItemPosition()).getRationId();

        }else if (parent == anyScholarship) {

            txtAnyScholarship = anyScholarshipList.get(anyScholarship.getSelectedItemPosition()).getRationId();

        }else if (parent == dropOutClass) {

            txtDropOutClass = dropOutClassList.get(dropOutClass.getSelectedItemPosition()).getRationId();

        }else if (parent == interestedCntStudy) {

            txtInterestedCntStudy = interestedCntStudyList.get(interestedCntStudy.getSelectedItemPosition()).getRationId();

        }else if (parent == awrAbtRehabPgm) {

            txtAwrAbtRehabPgm = awrAbtRehabPgmList.get(awrAbtRehabPgm.getSelectedItemPosition()).getRationId();

        }else if (parent == atnRehabPgm) {

            txtAtnRehabPgm = atnRehabPgmList.get(atnRehabPgm.getSelectedItemPosition()).getRationId();

        }else if (parent == immunization) {

            txtImmunization = immunizationList.get(immunization.getSelectedItemPosition()).getRationId();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
        haveVoterIdList = new ArrayList();
        vi = new ArrayAdapter(mContext, R.layout.spinner_item, haveVoterIdList);

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
        handicappedPensionList = new ArrayList();
        hp = new ArrayAdapter(mContext, R.layout.spinner_item, handicappedPensionList);

        illiterate = (Spinner) findViewById(R.id.illiterate);
        illiterateList = new ArrayList();
        ie = new ArrayAdapter(mContext, R.layout.spinner_item, illiterateList);

        memberOfShg = (Spinner) findViewById(R.id.memberOfshg);
        memberOfShgList = new ArrayList();
        mos = new ArrayAdapter(mContext, R.layout.spinner_item, memberOfShgList);

        haveSavingAcc = (Spinner) findViewById(R.id.havngSA);
        haveSavingAccList = new ArrayList();
        sa = new ArrayAdapter(mContext, R.layout.spinner_item, haveSavingAccList);

        aadharAvalble = (Spinner) findViewById(R.id.aadharAvailable);
        aadharAvalbleList = new ArrayList();
        aav = new ArrayAdapter(mContext, R.layout.spinner_item, aadharAvalbleList);

        studyingStd = (Spinner) findViewById(R.id.stdyngStndrd);
        studyingStdList = new ArrayList();
        ss = new ArrayAdapter(mContext, R.layout.spinner_item, studyingStdList);

        attndRehabSchool = (Spinner) findViewById(R.id.atnRehabSchl);
        attndRehabSchoolList = new ArrayList();
        ars = new ArrayAdapter(mContext, R.layout.spinner_item, attndRehabSchoolList);

        studyingInstType = (Spinner) findViewById(R.id.instituteType);
        studyingInstTypeList = new ArrayList();
        st = new ArrayAdapter(mContext, R.layout.spinner_item, studyingInstTypeList);

        anyScholarship = (Spinner) findViewById(R.id.gettingAnyScholarship);
        anyScholarshipList = new ArrayList();
        aSc = new ArrayAdapter(mContext, R.layout.spinner_item, anyScholarshipList);

        dropOutClass = (Spinner) findViewById(R.id.dropOutClass);
        dropOutClassList = new ArrayList();
        dc = new ArrayAdapter(mContext, R.layout.spinner_item, dropOutClassList);

        interestedCntStudy = (Spinner) findViewById(R.id.intrstCntnueStudy);
        interestedCntStudyList = new ArrayList();
        ics = new ArrayAdapter(mContext, R.layout.spinner_item, interestedCntStudyList);

        awrAbtRehabPgm = (Spinner) findViewById(R.id.awareAbtRehab);
        awrAbtRehabPgmList = new ArrayList();
        awrp = new ArrayAdapter(mContext, R.layout.spinner_item, awrAbtRehabPgmList);

        atnRehabPgm = (Spinner) findViewById(R.id.atndAnyRehab);
        atnRehabPgmList = new ArrayList();
        atrp = new ArrayAdapter(mContext, R.layout.spinner_item, atnRehabPgmList);

        immunization = (Spinner) findViewById(R.id.immunization);
        immunizationList = new ArrayList();
        imm = new ArrayAdapter(mContext, R.layout.spinner_item, immunizationList);
    }

    private void initCallback(){

        gender.setOnItemSelectedListener(this);
        religion.setOnItemSelectedListener(this);
        caste.setOnItemSelectedListener(this);
        occupation.setOnItemSelectedListener(this);
        nameInVoterList.setOnItemSelectedListener(this);
        haveVoterId.setOnItemSelectedListener(this);
        maritalStatus.setOnItemSelectedListener(this);
        widowPension.setOnItemSelectedListener(this);
        seniorCitizen.setOnItemSelectedListener(this);
        oldAgePension.setOnItemSelectedListener(this);
        handicapped.setOnItemSelectedListener(this);
        handicappedPension.setOnItemSelectedListener(this);
        illiterate.setOnItemSelectedListener(this);
        memberOfShg.setOnItemSelectedListener(this);
        haveSavingAcc.setOnItemSelectedListener(this);
        aadharAvalble.setOnItemSelectedListener(this);
        studyingStd.setOnItemSelectedListener(this);
        attndRehabSchool.setOnItemSelectedListener(this);
        studyingInstType.setOnItemSelectedListener(this);
        anyScholarship.setOnItemSelectedListener(this);
        dropOutClass.setOnItemSelectedListener(this);
        interestedCntStudy.setOnItemSelectedListener(this);
        awrAbtRehabPgm.setOnItemSelectedListener(this);
        atnRehabPgm.setOnItemSelectedListener(this);
        immunization.setOnItemSelectedListener(this);
    }
    private void spinnerDetails(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("pfijwt", MODE_PRIVATE);
        String stdt =pref.getString("StDt",null);
        JSONObject spinnerData = null;
        try {
            spinnerData = new JSONObject(stdt.toString().trim());
            String genderList = spinnerData.getString("genderType");
            // Log.d("State", statelist.toString().trim());
            JSONArray gen = new JSONArray(genderList.toString());
            for (int i = 0; i < gen.length(); i++) {
                JSONObject gendata = gen.getJSONObject(i);
                genKey = gendata.getString("key");
                genValue = gendata.getString("value");
                genList.add(new KeyValue(genKey, genValue));
                gr.notifyDataSetChanged();
            }

            String religionList = spinnerData.getString("religionList");
            // Log.d("State", statelist.toString().trim());
            JSONArray rel = new JSONArray(religionList.toString());
            for (int i = 0; i < rel.length(); i++) {
                JSONObject reldata = rel.getJSONObject(i);
                relKey = reldata.getString("key");
                relValue = reldata.getString("value");
                reliList.add(new KeyValue(relKey, relValue));
                rn.notifyDataSetChanged();
            }

            String csteList = spinnerData.getString("casteList");
            // Log.d("State", statelist.toString().trim());
            JSONArray cste = new JSONArray(csteList.toString());
            for (int i = 0; i < cste.length(); i++) {
                JSONObject castedata = cste.getJSONObject(i);
                casteKey = castedata.getString("key");
                casteValue = castedata.getString("value");
                casteList.add(new KeyValue(casteKey, casteValue));
                ce.notifyDataSetChanged();
            }

            String occuptnList = spinnerData.getString("occupationList");
            // Log.d("State", statelist.toString().trim());
            JSONArray occu = new JSONArray(occuptnList.toString());
            for (int i = 0; i < occu.length(); i++) {
                JSONObject occudata = occu.getJSONObject(i);
                occuKey = occudata.getString("key");
                occuValue = occudata.getString("value");
                occuList.add(new KeyValue(occuKey, occuValue));
                on.notifyDataSetChanged();
            }

            String ynzList = spinnerData.getString("YNZ");
            // Log.d("State", statelist.toString().trim());
            JSONArray ynz = new JSONArray(ynzList.toString());
            for (int i = 0; i < ynz.length(); i++) {
                JSONObject ynzdata = ynz.getJSONObject(i);
                ynzKey = ynzdata.getString("key");
                ynzValue = ynzdata.getString("value");
                nameInVotrList.add(new KeyValue(ynzKey, ynzValue));
                nv.notifyDataSetChanged();
                haveVoterIdList.add(new KeyValue(ynzKey, ynzValue));
                vi.notifyDataSetChanged();
                widowPensionList.add(new KeyValue(ynzKey,ynzValue));
                wp.notifyDataSetChanged();
                oldAgePensionList.add(new KeyValue(ynzKey,ynzValue));
                op.notifyDataSetChanged();
                handicappedPensionList.add(new KeyValue(ynzKey,ynzValue));
                hp.notifyDataSetChanged();
                anyScholarshipList.add(new KeyValue(ynzKey,ynzValue));
                aSc.notifyDataSetChanged();
                dropOutClassList.add(new KeyValue(ynzKey,ynzValue));
                dc.notifyDataSetChanged();
                interestedCntStudyList.add(new KeyValue(ynzKey,ynzValue));
                ics.notifyDataSetChanged();


            }

            String yNList = spinnerData.getString("YN");
            // Log.d("State", statelist.toString().trim());
            JSONArray yN = new JSONArray(yNList.toString());
            for (int i = 0; i < yN.length(); i++) {
                JSONObject electricitydata = yN.getJSONObject(i);
                ynKey = electricitydata.getString("key");
                ynValue = electricitydata.getString("value");
                seniorCitizenList.add(new KeyValue(ynKey, ynValue));
                sc.notifyDataSetChanged();
                handicappedList.add(new KeyValue(ynKey, ynValue));
                hc.notifyDataSetChanged();
                illiterateList.add(new KeyValue(ynKey, ynValue));
                ie.notifyDataSetChanged();
                memberOfShgList.add(new KeyValue(ynKey, ynValue));
                mos.notifyDataSetChanged();
                haveSavingAccList.add(new KeyValue(ynKey, ynValue));
                sa.notifyDataSetChanged();
                aadharAvalbleList.add(new KeyValue(ynKey, ynValue));
                aav.notifyDataSetChanged();
                attndRehabSchoolList.add(new KeyValue(ynKey, ynValue));
                ars.notifyDataSetChanged();
                awrAbtRehabPgmList.add(new KeyValue(ynKey, ynValue));
                awrp.notifyDataSetChanged();
                atnRehabPgmList.add(new KeyValue(ynKey, ynValue));
                atrp.notifyDataSetChanged();
                immunizationList.add(new KeyValue(ynKey, ynValue));
                imm.notifyDataSetChanged();
            }

            String maritalList = spinnerData.getString("maritalStatus");
            // Log.d("State", statelist.toString().trim());
            JSONArray marital = new JSONArray(maritalList.toString());
            for (int i = 0; i < marital.length(); i++) {
                JSONObject maritaldata = marital.getJSONObject(i);
                maritalStatusKey = maritaldata.getString("key");
                maritalStatusValue = maritaldata.getString("value");
                maritalStatusList.add(new KeyValue(maritalStatusKey, maritalStatusValue));
                ms.notifyDataSetChanged();
            }

            String studystdList = spinnerData.getString("studyingClassType");
            // Log.d("State", statelist.toString().trim());
            JSONArray studyStd = new JSONArray(studystdList.toString());
            for (int i = 0; i < studyStd.length(); i++) {
                JSONObject studyStddata = studyStd.getJSONObject(i);
                studyingStdKey = studyStddata.getString("key");
                studyingStdValue = studyStddata.getString("value");
                studyingStdList.add(new KeyValue(studyingStdKey, studyingStdValue));
                ss.notifyDataSetChanged();
            }

            String studyinstypList = spinnerData.getString("institutionType");
            // Log.d("State", statelist.toString().trim());
            JSONArray studyinst = new JSONArray(studyinstypList.toString());
            for (int i = 0; i < studyinst.length(); i++) {
                JSONObject studyInsTypedata = studyinst.getJSONObject(i);
                studyingInstTypeKey = studyInsTypedata.getString("key");
                studyingInstTypeValue = studyInsTypedata.getString("value");
                studyingStdList.add(new KeyValue(studyingInstTypeKey, studyingInstTypeValue));
                st.notifyDataSetChanged();
            }

            String studydropList = spinnerData.getString("dropoutClassType");
            // Log.d("State", statelist.toString().trim());
            JSONArray studydrop = new JSONArray(studydropList.toString());
            for (int i = 0; i < studydrop.length(); i++) {
                JSONObject studydropdata = studydrop.getJSONObject(i);
                dropOutClassKey = studydropdata.getString("key");
                dropOutClassValue = studydropdata.getString("value");
                dropOutClassList.add(new KeyValue(dropOutClassKey, dropOutClassValue));
                dc.notifyDataSetChanged();
            }


        }catch (JSONException e) {
                e.printStackTrace();

            }


    }
    private void initSpinner(){
        gr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(gr);

        rn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religion.setAdapter(rn);

        ce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        caste.setAdapter(ce);

        on.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occupation.setAdapter(on);

        nv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameInVoterList.setAdapter(nv);

        vi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        haveVoterId.setAdapter(vi);

        ms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatus.setAdapter(ms);

        wp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widowPension.setAdapter(wp);

        sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seniorCitizen.setAdapter(sc);

        op.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oldAgePension.setAdapter(op);

        hc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handicapped.setAdapter(hc);

        hp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        handicappedPension.setAdapter(hp);

        ie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        illiterate.setAdapter(ie);

        mos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        memberOfShg.setAdapter(mos);

        sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        haveSavingAcc.setAdapter(sa);

        aav.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aadharAvalble.setAdapter(aav);

        ss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studyingStd.setAdapter(ss);

        ars.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attndRehabSchool.setAdapter(ars);

        st.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studyingInstType.setAdapter(st);

        aSc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anyScholarship.setAdapter(aSc);

        dc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropOutClass.setAdapter(dc);

        ics.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedCntStudy.setAdapter(ics);

        awrp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        awrAbtRehabPgm.setAdapter(awrp);

        atrp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        atnRehabPgm.setAdapter(atrp);

        imm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        immunization.setAdapter(imm);
    }

}
