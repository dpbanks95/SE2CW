package uk.ac.uea.activityprogram;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity {
    private static final String TAG = "SchoolActivity";
    private DatabaseHelper db;
    private ListView activityLv;
    private String selectedSchool;
    //private ActivityListAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ActivityModel> activityList;
    private CheckBox remind;
    View.OnClickListener snackbarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        db = DatabaseHelper.getInstance(this);
        Intent i = getIntent();
        TextView schoolTitle = (TextView) findViewById(R.id.schoolNameTxt);
        selectedSchool = i.getStringExtra("selectedSchool");
        schoolTitle.setText(selectedSchool);
        //selectedSchool = "CMP";
        activityList = db.getAllActivitiesBySchool(selectedSchool);
//        ActivityModel am = new ActivityModel();
//        am.setTitle("Our title");
//        am.setRoomNumber("The room");
//        am.setTime("9PM");
//        ArrayList<ActivityModel> amArray = new ArrayList();
//        amArray.add(am);
        recyclerView = (RecyclerView) findViewById(R.id.activitiesRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ActivityRecyclerAdapter(this, activityList);
        recyclerView.setAdapter(adapter);
        //backToHome();

        //activityLv = (ListView) findViewById(R.id.activitiesLst);
        //loadActivityData(db, selectedSchool, activityLv);


//        remind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d(TAG, buttonView.toString());
//                Log.d(TAG, Boolean.toString(isChecked));
////                RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_school);
////                if (isChecked) {
////                    String set = "Reminder set for ";
////                    Snackbar sb = Snackbar.make(layout, set, Snackbar.LENGTH_LONG)
////                            .setAction("Undo", snackbarListener);
////                    remind.setChecked(true);
////                    sb.show();
////                }
////                else {
////                    String unset = "Reminder removed for ";
////                    Snackbar usb = Snackbar.make(layout, unset, Snackbar.LENGTH_SHORT);
////                    remind.setChecked(false);
////                    usb.show();
////                }
//            }
//        });
//
//
////        activityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                RelativeLayout layout = (RelativeLayout)findViewById(R.id.activity_school);
////                String selected = mActivityList.get(position).getTitle();
////                remind = (CheckBox) view.findViewById(R.id.remindChk);
////                if (!remind.isChecked()) {
////                    String set = "Reminder set for " + selected;
////                    Snackbar sb = Snackbar.make(layout, set, Snackbar.LENGTH_LONG)
////                            .setAction("Undo", snackbarListener);
////                    remind.setChecked(true);
////                    sb.show();
////                }
////                else {
////                    String unset = "Reminder removed for " + selected;
////                    Snackbar usb = Snackbar.make(layout, unset, Snackbar.LENGTH_SHORT);
////                    remind.setChecked(false);
////                    usb.show();
////                }
////            }
////        });
//
//        snackbarListener  = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                remind.setChecked(false);
//            }
//        };
        }

//    public void loadActivityData(DatabaseHelper dbH, String selectedSchool, ListView lv){
//        activityList = dbH.getAllActivitiesBySchool(selectedSchool);
//
//        adapter = new ActivityListAdapter(getApplicationContext(), activityList);
//        activityLv.setAdapter(adapter);
//    }
//
//    public void backToHome(){
//        Button back = (Button)findViewById(R.id.backBtn);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
//            }
//        });
//    }
}
