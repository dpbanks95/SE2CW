package uk.ac.uea.activityprogram;



public class Activity {
    private int id;
    private String title;
    private String roomNumber;
    private String time;
    private boolean reminder;

    public Activity(int id, String title, String roomNumber, String time) {
        this.id = id;
        this.title = title;
        this.roomNumber = roomNumber;
        this.time = time;
        this.reminder = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
