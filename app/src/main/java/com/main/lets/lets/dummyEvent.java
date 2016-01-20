package com.main.lets.lets;

import java.sql.Date;
import java.sql.Time;

public class dummyEvent implements Eventable {

    String[] args = null;
    String[] restrictions = null;
    String title = "";
    String description = "";
    Time startTime = new Time(0);
    Time endTime = new Time(0);
    Date date = new Date(0);
    boolean isAttending = false;
    double[] coords;
    int minA = 0;
    int maxA = 0;
    private String tags;

    public dummyEvent() {
        String[] args = {
                "Ice Cream Social",
                "Union Patio",
                "5:00PM - 7:00AM",
                "Come and get ice cream! " +
                        "all the flavor you want! " +
                        "and free cups!" +
                        "Come!!!", "3", "100"};

        String[] restrictions = {"18+", "Invite Only"};

        this.restrictions = restrictions;
        this.args = args;
        this.title = args[0];
        //Add location variable
        this.startTime = new Time(23, 0, 0);
        this.endTime = new Time(4, 0, 0);
        this.description = args[3];
        this.minA = 10;
        this.maxA = Integer.parseInt(args[5]);
        this.coords = new double[]{39.4840838, -87.3211234};

    }

    @Override
    public String[] getRestrictions() {

        return this.restrictions;
    }

    @Override
    public boolean isAttending() {

        return isAttending;
    }

    @Override
    public void setAttending(boolean b) {
        this.isAttending = b;

    }

    @Override
    public void setTitle(String s) {
        this.title = s;

    }

    @Override
    public String getTitle() {

        return this.title;
    }

    @Override
    public void setDescription(String s) {
        this.description = s;

    }

    @Override
    public String getDescription() {

        return this.description;
    }

    @Override
    public void setStartTime(Time t) {
        this.startTime = t;

    }

    @Override
    public String getStartTimeString() {

        return this.startTime.toString();
    }

    @Override
    public void setEndTime(Time t) {
        this.endTime = t;

    }

    @Override
    public String getEndTimeString() {

        return this.endTime.toString();
    }

    @Override
    public void setMinA(int i) {
        this.minA = i;

    }

    @Override
    public void setMaxA(int i) {
        this.maxA = i;

    }

    @Override
    public int getMaxA() {

        return this.maxA;
    }

    @Override
    public String getTags() {
        return this.tags;
    }


    @Override
    public void setDate(Date d) {
        this.date = d;

    }

    @Override
    public String getDateString() {

        return this.date.toString();
    }

    @Override
    public void setCoords(double x, double y) {
        this.coords = new double[]{x, y};

    }

    @Override
    public double[] getCoords() {

        return this.coords;
    }
}
