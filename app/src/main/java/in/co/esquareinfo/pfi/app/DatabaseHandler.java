package in.co.esquareinfo.pfi.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by azar on 09-07-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, "survey", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createSurveyTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS survey";
        String drop2Table = "DROP TABLE IF EXISTS family";
        db.execSQL(dropTable);
        db.execSQL(drop2Table);
        onCreate(db);
    }

    private void createSurveyTable(SQLiteDatabase db) {
        String createTable = "CREATE TABLE survey (id INTEGER, user_id TEXT, familyName TEXT, address TEXT, rationCard TEXT, electricity TEXT, avgMonthlyIncome TEXT, avgMonthlyExpense TEXT, drinkingWaterSource TEXT," +
                "toilet TEXT, healthCare TEXT, ashaWorkerComing TEXT, foodGettingFromAnganwadi TEXT, houseBuildingType TEXT, houseOwnership TEXT, noOfVehicles TEXT, typeOfVehicles TEXT, cookingBy TEXT, landOwned TEXT," +
                "isRehabBeneficial TEXT, childStartedGoingSchool TEXT, gotFreeRemedialCoaching TEXT, freeMedicalCare TEXT, awarenessAbtGovtSchemes TEXT, moreAttentionOnChildEducation TEXT, villageId TEXT, upload INTEGER)";
        //Startdate,Enddatetime,Uploaded 0/1, gps
        db.execSQL(createTable);

        String createFamilyTable = "CREATE TABLE family (id INTEGER PRIMARY KEY,survey_id INTEGER, personName INTEGER, personDob TEXT, gender TEXT, personMobNo TEXT, religion TEXT, caste TEXT, occupation TEXT, nameInVotersList TEXT, haveAVoterId Text, maritalStatus TEXT, gettingWidowPension TEXT, seniorCitizen TEXT," +
                "gettingOldAgePension TEXT, handicapped TEXT, gettingPensionForHandicapped TEXT, illiterate TEXT, memberOfShg TEXT, haveSavingsAccount TEXT, aadharAvailable TEXT, studyingStandard TEXT, attendingRehabSchool TEXT, studyingInstitutionName TEXT, studyingInstitutionType TEXT, gettingAnyScholarship TEXT, " +
                "gettingScholarshipType TEXT, studyDropOutYear TEXT, studyDropOutClass TEXT, studyDropOutReason TEXT, interestedToContinueStudy TEXT, awareAboutRehabPgms TEXT, attendedAnyRehabPgm TEXT, immunization TEXT )";
        db.execSQL(createFamilyTable);
    }

    public void addSurvey(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);

        database.insert("survey", null, values);
        database.close();
    }

    public void updateSurvey(int id, List<Database> surveys) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (Database survey : surveys) {
            values.put(survey.getmColumn(), survey.getmValue());
        }

        database.update("survey", values, "id = ?", new String[]{String.valueOf(id)});
        database.close();
    }

    public void uploaded() {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("upload", 1);

        database.update("survey", values, null, null);
        database.close();
    }

    public void addFamily(List<DatabaseFamily> family1) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (DatabaseFamily family : family1) {
            values.put(family.getmColumn(), family.getmValue());
        }

        database.insert("family", null, values);
        database.close();
    }

    public JSONArray getJsonRequest() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        String selectQuerySurvey = "SELECT * FROM survey where upload = 0";

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursorSurvey = database.rawQuery(selectQuerySurvey, null);
        if (cursorSurvey.moveToFirst()) {
            do {
                JSONObject jsonObject = new JSONObject();
                int surveyId = 0;
                for (int i = 0; i <cursorSurvey.getColumnCount(); i++) {
                    if (i == 0) surveyId = cursorSurvey.getInt(i);
                    else jsonObject.put(cursorSurvey.getColumnName(i), cursorSurvey.getString(i));
                }
                JSONArray familyArray = new JSONArray();
                String selectQueryFamily = "SELECT * FROM family where survey_id = " + surveyId;
                Cursor cursorFamily = database.rawQuery(selectQueryFamily, null);
                if (cursorFamily.moveToFirst()) {
                    do {
                        JSONObject familyObject = new JSONObject();
                        for (int j = 2; j < cursorFamily.getColumnCount(); j++) {
                            familyObject.put(cursorFamily.getColumnName(j), cursorFamily.getString(j));
                        }
                        familyArray.put(familyObject);
                    } while (cursorFamily.moveToNext());
                    cursorFamily.close();
                }
                jsonObject.put("family", familyArray);
                jsonArray.put(jsonObject);
            } while (cursorSurvey.moveToNext());
            cursorSurvey.close();
        }
        database.close();
        return jsonArray;
    }

  /*  public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, "survey");
        db.close();
        return cnt;
    }*/

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM survey WHERE upload = 0";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}

