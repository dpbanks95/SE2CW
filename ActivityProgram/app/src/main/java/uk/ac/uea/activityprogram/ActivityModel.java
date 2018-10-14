package uk.ac.uea.activityprogram;

import java.util.Date;

/**
 * Created by ChanelleRichardson on 21/11/2016.
 */


public class ActivityModel {
    private int id;
    private String title;
    private String school;
    private String roomNumber;
    private String time;
    private Date date;
    private boolean saved;

    public ActivityModel(){
        this.id = 0;
        this.title = null;
        this.school = null;
        this.roomNumber = null;
        this.time = null;
        this.date = null;
        this.saved = false;

    }
    public ActivityModel(int id, String title, String school, String roomNumber, String time, Date date, boolean saved) {
        this.id = id;
        this.title = title;
        this.school = school;
        this.roomNumber = roomNumber;
        this.time = time;
        this.date = date;
        this.saved = saved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }


}
