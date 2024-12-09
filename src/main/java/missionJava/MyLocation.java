package missionJava;

import java.util.Date;

public class MyLocation {
    int id;
    double myLat;
    double myLnt;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMyLat() {
        return myLat;
    }

    public void setMyLat(double myLat) {
        this.myLat = myLat;
    }

    public double getMyLnt() {
        return myLnt;
    }

    public void setMyLnt(double myLnt) {
        this.myLnt = myLnt;
    }
}
