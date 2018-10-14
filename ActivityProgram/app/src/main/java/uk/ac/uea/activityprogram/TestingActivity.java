package uk.ac.uea.activityprogram;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class TestingActivity extends AppCompatActivity {
    DatabaseHelper activityDatabase;
    private static final String TAG = "mainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        activityDatabase = new DatabaseHelper(this);
        setContentView(R.layout.activity_testing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        populateDB();
    }

    public void populateDB(){

        //ArrayList array_list = activityDatabase.getAllActivities();
        //ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        Date d = new Date("10/1/2012");

        //TODO change resulttext to display within the activities list results.
        //ListView rt = (ListView) findViewById(R.id.activitiesLst);
        try {
            activityDatabase.insertActivities(1, "Progintro1", "CMP", "Sci21", "11:00", "10-01-2016");
            activityDatabase.insertActivities(2, "Progintro2", "ENV", "Sci22", "12:00", "10-01-2016");
            activityDatabase.insertActivities(3, "Progintro3", "CMP", "Sci23", "13:00", "10-01-2016");
            activityDatabase.insertActivities(4, "Progintro4", "BIO", "Sci24", "14:00", "10-01-2016");
//            activityDatabase.insertOpenDay(1,d);
//            activityDatabase.insertOpenDay(2,d);
//            activityDatabase.insertOpenDay(3,d);
//            activityDatabase.insertOpenDay(4,d);
            activityDatabase.insertSchools("CMP", "Computing Sciences", "This is the UEA school of Comuting Sciences");
            activityDatabase.insertSchools("ENV", "Environmental Sciences", "This is the UEA school of Environmental Sciences");
            activityDatabase.insertSchools("BIO", "Biology", "This is the UEA school of Biology");

        }
        catch (Exception e){
            Toast.makeText(TestingActivity.this, "ERROR "+e.toString(), Toast.LENGTH_LONG).show();
            //rt.setS(e.toString());
        }
        try {
            ActivityModel result = activityDatabase.getSingleActivity(2);
            //Integer result = activityDatabase.numberOfCol();
            String s = result.toString();
            //rt.setText(s);
        }
        catch (Exception e){
            Toast.makeText(TestingActivity.this, "ERROR2 "+e.toString(), Toast.LENGTH_LONG).show();
            //rt.setText(e.toString());
        }
    }
}
