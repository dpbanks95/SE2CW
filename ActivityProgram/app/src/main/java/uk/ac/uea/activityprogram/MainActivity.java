package uk.ac.uea.activityprogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper activityDatabase;
    private static final String TAG = "mainActivity";
    private Spinner openDaySelector;
    private ListView listOfSchools;

    String chosenDate = "10-01-2016"; //test date value to see if list if schools available on selected openDay can be returned by database.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityDatabase = DatabaseHelper.getInstance(this);
        openDaySelector = (Spinner)findViewById(R.id.openDayDateSpn);
        listOfSchools = (ListView)findViewById(R.id.schoolsLst);

        populateDB();
        loadSpinnerData(activityDatabase, openDaySelector);
        chosenDate = (String)openDaySelector.getItemAtPosition(0);
        loadListData(activityDatabase, chosenDate, listOfSchools);


        openDaySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenDate = (String)openDaySelector.getItemAtPosition(position);
//                Log.d(TAG, "Date = " + chosenDate);
                loadListData(activityDatabase, chosenDate, listOfSchools);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listOfSchools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSchool = (String)(listOfSchools.getItemAtPosition(position));
                Log.d(TAG, "School =  " + selectedSchool);
                Intent i = new Intent(getApplicationContext(), SchoolActivity.class);
                i.putExtra("selectedSchool", selectedSchool);
                startActivity(i);
            }
        });



//        try {
//            ActivityModel result = activityDatabase.getSingleActivity(2);
//            //Integer result = activityDatabase.numberOfCol();
//            String s = result.toString();
//            //rt.setText(s);
//        }
//        catch (Exception e){
//            Toast.makeText(MainActivity.this, "ERROR2 "+e.toString(), Toast.LENGTH_LONG).show();
//            //rt.setText(e.toString());
//        }
    }

//    public Date dates(int day, int month, int year){
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.MONTH, month);
//        cal.set(Calendar.DAY_OF_MONTH, day);
//        return cal.getTime();
//    }

    //TEST DATABASE POPULATION.

    public void populateDB(){

        //ArrayList array_list = activityDatabase.getAllActivities();
        //ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);



        //ListView rt = (ListView) findViewById(R.id.activitiesLst);
        try {
//            activityDatabase.insertOpenDay(1,d);
//            activityDatabase.insertOpenDay(2,d);
//            activityDatabase.insertOpenDay(3,d);
//            activityDatabase.insertOpenDay(4,d);
            activityDatabase.insertActivities(1, "Progintro1", "CMP", "Sci21", "11:00", "10-01-2016");
            activityDatabase.insertActivities(2, "Progintro2", "ENV", "Sci22", "12:00", "11-01-2016");
            activityDatabase.insertActivities(3, "Progintro3", "CMP", "Sci23", "13:00", "12-01-2016");
            activityDatabase.insertActivities(4, "Progintro4", "BIO", "Sci24", "14:00", "10-01-2016");
            activityDatabase.insertActivities(5, "Progintro5", "CMP", "Sci25", "15:00", "10-01-2016");
            activityDatabase.insertActivities(6, "Progintro6", "CMP", "Sci26", "16:00", "11-01-2016");
            activityDatabase.insertActivities(7, "Progintro7", "CMP", "Sci27", "17:00", "10-01-2016");
            activityDatabase.insertActivities(8, "Progintro8", "BIO", "Sci28", "18:00", "12-02-2016");

            activityDatabase.updateActivities(2,"envintro", "ENV", "sci 3.01", "10:00", "11-01-2016");

            activityDatabase.insertSchools("CMP", "Computing Sciences", "This is the UEA school of Computing Sciences");
            activityDatabase.insertSchools("ENV", "Environmental Sciences", "This is the UEA school of Environmental Sciences");
            activityDatabase.insertSchools("BIO", "Biology", "This is the UEA school of Biology");

        }
        catch (Exception e){
            Toast.makeText(MainActivity.this, "ERROR "+e.toString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, e.toString());
            //rt.setS(e.toString());
        }
    }



    public void loadSpinnerData(DatabaseHelper activityDb, Spinner spn){

        ArrayList<String> data = activityDb.getAllOpenDays();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn.setAdapter(dataAdapter);
    }

    public void loadListData(DatabaseHelper activityDb, String chosenDate, ListView lst){

        ArrayList<String> data = activityDb.getAllSchoolsOnOpenDay(chosenDate);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        lst.setAdapter(dataAdapter);

    }
}
