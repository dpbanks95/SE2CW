package uk.ac.uea.activityprogram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by ChanelleRichardson on 21/11/2016.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 19;
    private static DatabaseHelper instance = null;


    private static final String TAG = "ActivityPlannerDb";

    protected DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }


    private static final String DATABASE_NAME = "activityPlannerDB";

    //Tables
    private static final String TABLE_ACTIVITIES = "activities";
    private static final String TABLE_SCHOOLS = "schools";
//    private static final String TABLE_OPENDAYS = "openDays";

    //columns
    //repeated columns

    private static final String KEY_COLUMN_SCHOOLCODE = "schoolCode"; //sets school code as key for school table and school identifier in activities table.

    //Activities columns
    private static final String ACTIVITIES_COLUMN_ID = "id";
    private static final String ACTIVITIES_COLUMN_TITLE = "title";
    private static final String ACTIVITIES_COLUMN_ROOMNUMBER = "roomNumber";
    private static final String ACTIVITIES_COLUMN_TIME = "time";
    private static final String ACTIVITIES_COLUMN_OPENDAY = "openDay";
    private static final String ACTIVITIES_COLUMN_SAVED = "saved";

    //Schools columns
    private static final String SCHOOLS_COLUMN_TITLE = "schoolTitle";
    private static final String SCHOOLS_COLUMN_DESCRIPTION = "schoolDescription";

    //OpenDayDates columns
    //private static final String OPENDAYS_COLUMN_DATE = "date";

    //Create all table methods
//Activities Table creation
    private static final String CREATE_TABLE_ACTIVITIES = "CREATE TABLE " + TABLE_ACTIVITIES
            + "( " + ACTIVITIES_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + ACTIVITIES_COLUMN_TITLE + " TEXT, "
            + KEY_COLUMN_SCHOOLCODE + " TEXT,  "
            + ACTIVITIES_COLUMN_ROOMNUMBER + " TEXT, "
            + ACTIVITIES_COLUMN_TIME + " TEXT, "
            + ACTIVITIES_COLUMN_OPENDAY + " TEXT, "
            + ACTIVITIES_COLUMN_SAVED + " BOOLEAN, "
            + "FOREIGN KEY (" + KEY_COLUMN_SCHOOLCODE + ") REFERENCES " + TABLE_SCHOOLS + "(" + KEY_COLUMN_SCHOOLCODE + "))";


    //Schools Table creation
    private static final String CREATE_TABLE_SCHOOLS = "CREATE TABLE " + TABLE_SCHOOLS
            + "( " + KEY_COLUMN_SCHOOLCODE + " TEXT PRIMARY KEY, "
            + SCHOOLS_COLUMN_TITLE + " TEXT, "
            + SCHOOLS_COLUMN_DESCRIPTION + " TEXT " + ")";


//    //Open day Date Table creation
//    private static final String CREATE_TABLE_OPENDAYS = "CREATE TABLE " + TABLE_OPENDAYS
//            + "( " + KEY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
//            + OPENDAYS_COLUMN_DATE + "TEXT " + ")";

    //onCreate method
    @Override
    public void onCreate(SQLiteDatabase activityDb) {
        activityDb.execSQL(CREATE_TABLE_ACTIVITIES);
        activityDb.execSQL(CREATE_TABLE_SCHOOLS);
//        activityDb.execSQL(CREATE_TABLE_OPENDAYS);
    }

    //onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase activityDb, int oldVersion, int newVersion) {
        activityDb.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        activityDb.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOLS);
//        activityDb.execSQL("DROP TABLE IF EXISTS " + TABLE_OPENDAYS);
        onCreate(activityDb);
    }

    public int numberOfCol() {
        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_ACTIVITIES, null);
        return res.getColumnCount();
    }

    public Date stringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        ParsePosition pos = new ParsePosition(0);
        Date convertedDate = dateFormat.parse(date, pos);
        return convertedDate;
    }

//    public String dateToString(Date date) {
//        String sqlDatePattern = "EEE MMM dd kk:mm:ss z yyyy";
//        String newDatePattern = "dd-MM-yyyy";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(newDatePattern);
//        String openDayString = dateFormat.format(date);
//        return openDayString;
//    }
//
    //TABLE_ACTIVITIES methods
    public boolean insertActivities(Integer id, String title, String school, String roomNumber, String time, String openDay) {
//        String sqlDatePattern = "EEE MMM dd kk:mm:ss z yyyy";
//        String newDatePattern = "dd-MM-yyyy";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(newDatePattern);
//        String openDayString = dateFormat.format(openDay);


        SQLiteDatabase activityDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("schoolCode", school);
        contentValues.put("roomNumber", roomNumber);
        contentValues.put("time", time);
        contentValues.put("openDay", openDay);
        activityDb.insert(TABLE_ACTIVITIES, null, contentValues);
        return true;

    }

    public ActivityModel getSingleActivity(int id) {
//        String s = "";
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_ACTIVITIES + " WHERE ID=" + ID + "", null);
//        //Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_ACTIVITIES, null);
//        res.moveToFirst();
//        s = res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TITLE));

        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_ACTIVITIES + " WHERE ID=" + id + "", null);
        res.moveToFirst();


        ActivityModel am = new ActivityModel();
        am.setId(res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_ID)));
        am.setTitle(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TITLE)));
        am.setSchool(res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
        am.setRoomNumber(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_ROOMNUMBER)));
        am.setTime(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TIME)));
        am.setDate(stringToDate(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_OPENDAY))));
        boolean isSaved = res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_SAVED)) != 0;
        am.setSaved(isSaved);
        return am;
    }


    public ArrayList<ActivityModel> getAllActivities() {
        ArrayList<ActivityModel> array_list = new ArrayList<ActivityModel>();

        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("select * from " + TABLE_ACTIVITIES, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            //ActivityModel am = new ActivityModel();

//            am.setId(res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_ID)));
//            am.setTitle(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TITLE)));
//            am.setSchool(res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
//            am.setRoomNumber(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_ROOMNUMBER)));
//            am.setTime(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TIME)));
//            am.setDate(openDayDate);
//            boolean isSaved = res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_SAVED)) != 0;
//            am.setSaved(isSaved);

            int id = (res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_ID)));
            String title = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TITLE)));
            String school = (res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
            String roomNum = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_ROOMNUMBER)));
            String time = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TIME)));
            Date openDay = stringToDate(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_OPENDAY)));
            boolean isSaved = res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_SAVED)) != 0;
            ActivityModel am = new ActivityModel(id, title, school, roomNum, time, openDay, isSaved);

            array_list.add(am);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllOpenDays() {
        ArrayList<String> datesArrayList = new ArrayList<String>();
        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("SELECT DISTINCT " + ACTIVITIES_COLUMN_OPENDAY + " FROM " + TABLE_ACTIVITIES, null);
        res.moveToFirst();


        while (res.isAfterLast() == false) {
            datesArrayList.add(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_OPENDAY)));
            res.moveToNext();
        }
        return datesArrayList;
    }



    public ArrayList<String> getAllSchoolsOnOpenDay(String chosenOpenDay){
        ArrayList<String> schoolsArrayList = new ArrayList<String>();
        SQLiteDatabase activityDb = this.getReadableDatabase();

        Cursor res = activityDb.rawQuery("SELECT DISTINCT " + KEY_COLUMN_SCHOOLCODE + " FROM "
                + TABLE_ACTIVITIES + " WHERE " + ACTIVITIES_COLUMN_OPENDAY + " =?; ", new String[]{chosenOpenDay}, null);
//
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            schoolsArrayList.add(res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
            res.moveToNext();
        }
        return schoolsArrayList;
    }

    public ArrayList<ActivityModel> getAllActivitiesBySchool(String school){
        ArrayList<ActivityModel> activitiesArrayList = new ArrayList<ActivityModel>();
        SQLiteDatabase activityDb = this.getReadableDatabase();

        Cursor res = activityDb.rawQuery("SELECT * FROM "
                + TABLE_ACTIVITIES + " WHERE " + KEY_COLUMN_SCHOOLCODE + " =?; ", new String[]{school}, null);
//
        res.moveToFirst();
        try {
            while (res.isAfterLast() == false) {
                int id = (res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_ID)));
                String title = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TITLE)));
                String schoolcode = (res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
                String roomNum = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_ROOMNUMBER)));
                String time = (res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_TIME)));
                Date openDay = stringToDate(res.getString(res.getColumnIndex(ACTIVITIES_COLUMN_OPENDAY)));
                boolean isSaved = res.getInt(res.getColumnIndex(ACTIVITIES_COLUMN_SAVED)) != 0;
                ActivityModel am = new ActivityModel(id, title, schoolcode, roomNum, time, openDay, isSaved);
                activitiesArrayList.add(am);
                res.moveToNext();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
        return activitiesArrayList;
    }



    public boolean updateActivities(Integer id, String title, String school, String roomNumber, String time, String openDay) {
        SQLiteDatabase activityDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("schoolCode", school);
        contentValues.put("roomNumber", roomNumber);
        contentValues.put("time", time);
        contentValues.put("openDay", openDay);
        activityDb.update("activities", contentValues, "ID = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    public Integer deleteActivity(Integer id) {
        SQLiteDatabase activityDb = this.getWritableDatabase();
        return activityDb.delete("activities",
                "ID = ? ",
                new String[]{Integer.toString(id)});
    }


    //TABLE_SCHOOLS methods
    public boolean insertSchools(String schoolCode, String schoolTitle, String schoolDescription) {
        SQLiteDatabase activityDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("schoolCode", schoolCode);
        contentValues.put("schoolTitle", schoolTitle);
        contentValues.put("schoolDescription", schoolDescription);
        return true;
    }


    public SchoolModel getSingleSchool(String schoolCode) {
        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_SCHOOLS + "WHERE " + KEY_COLUMN_SCHOOLCODE + " =" + schoolCode + "", null);
        SchoolModel sm = new SchoolModel();
        sm.setSchoolCode(res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
        sm.setSchoolTitle(res.getString(res.getColumnIndex(SCHOOLS_COLUMN_TITLE)));
        sm.setSchoolDescription(res.getString(res.getColumnIndex(SCHOOLS_COLUMN_DESCRIPTION)));
        return sm;
    }

//    public Cursor getSingleSchoolCode(String schoolCode) {
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("SELECT " + KEY_COLUMN_SCHOOLCODE + " FROM " + TABLE_SCHOOLS + "WHERE " + KEY_COLUMN_SCHOOLCODE + " =" + schoolCode + "", null);
//        return res;
//    }

    public ArrayList<SchoolModel> getAllSchools() {
        ArrayList<SchoolModel> array_list = new ArrayList<SchoolModel>();

        SQLiteDatabase activityDb = this.getReadableDatabase();
        Cursor res = activityDb.rawQuery("select * from " + TABLE_SCHOOLS, null);
        res.moveToFirst();
        SchoolModel sm = new SchoolModel();
        sm.setSchoolCode(res.getString(res.getColumnIndex(KEY_COLUMN_SCHOOLCODE)));
        sm.setSchoolTitle(res.getString(res.getColumnIndex(SCHOOLS_COLUMN_TITLE)));
        sm.setSchoolDescription(res.getString(res.getColumnIndex(SCHOOLS_COLUMN_DESCRIPTION)));

        array_list.add(sm);
        return array_list;
    }


    public boolean updateSchools(String schoolCode, String schoolTitle, String schoolDescription) {
        SQLiteDatabase activityDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("schoolCode", schoolCode);
        contentValues.put("schoolTitle", schoolTitle);
        contentValues.put("schoolDescription", schoolDescription);

        activityDb.update(TABLE_SCHOOLS, contentValues, KEY_COLUMN_SCHOOLCODE + " = ? ", new String[]{schoolCode});
        return true;
    }

    public Integer deleteASchool(String schoolCode) {
        SQLiteDatabase activityDb = this.getWritableDatabase();
        return activityDb.delete(TABLE_SCHOOLS,
                KEY_COLUMN_SCHOOLCODE + " = ? ",
                new String[]{schoolCode});
    }


//TABLE_OPENDAYS methods
//    public boolean insertOpenDay(Integer id, Date date) {
//        SQLiteDatabase activityDb = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
//        contentValues.put("date", date.toString());
//        return true;
//    }
//
//    public Cursor getSingleOpenDayRow(int ID) {
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("SELECT * FROM " + TABLE_OPENDAYS + "WHERE ID=" + ID + "", null);
//        return res;
//    }
//
//    public Cursor getSingleOpenDayDate(int ID) {
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("SELECT " + OPENDAYS_COLUMN_DATE + " FROM " + TABLE_OPENDAYS + "WHERE ID=" + ID + "", null);
//        return res;
//    }
//
//    public ArrayList<String> getAllOpenDayRows() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("select * from " + TABLE_OPENDAYS, null);
//        res.moveToFirst();
//        return array_list;
//    }
//
//    public ArrayList<String> getAllOpenDayDates() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        SQLiteDatabase activityDb = this.getReadableDatabase();
//        Cursor res = activityDb.rawQuery("select " + OPENDAYS_COLUMN_DATE + " from " + TABLE_OPENDAYS, null);
//        res.moveToFirst();
//        return array_list;
//    }
//
//    public boolean updateOpenDay(Integer id, Date openDay) {
//        SQLiteDatabase activityDb = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", id);
//        contentValues.put("openDay", openDay.toString());
//
//        activityDb.update(TABLE_OPENDAYS, contentValues, "ID = ? ", new String[]{Integer.toString(id)});
//        return true;
//    }
//
//    public Integer deleteOpenDay(Integer ID) {
//        SQLiteDatabase activityDb = this.getWritableDatabase();
//        return activityDb.delete(TABLE_OPENDAYS,
//                "ID = ? ",
//                new String[]{Integer.toString(ID)});
//    }
//

}
