package uk.ac.uea.activityprogram;

/**
 * Created by ChanelleRichardson on 21/11/2016.
 */

public class SchoolModel {
    private String schoolCode;
    private String schoolTitle;
    private String schoolDescription;


    public SchoolModel(){

    }

    public SchoolModel(String schoolCode, String schoolTitle, String schoolDescription) {
        this.schoolCode = schoolCode;
        this.schoolTitle = schoolTitle;
        this.schoolDescription = schoolDescription;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolTitle() {
        return schoolTitle;
    }

    public void setSchoolTitle(String schoolTitle) {
        this.schoolTitle = schoolTitle;
    }

    public String getSchoolDescription() {
        return schoolDescription;
    }

    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }



}
