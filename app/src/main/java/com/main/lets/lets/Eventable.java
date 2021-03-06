package com.main.lets.lets;

import java.sql.Date;
import java.sql.Time;

public interface Eventable {

    /**
     * This method returns an array of strings with an arbitrary amount of
     * elements based on how many restrictions the event has. Ex. "18+", "21+",
     * "Female/male only", ect.
     *
     * @return An array of all active restrictions on the event.
     */
    String[] getRestrictions();

    /**
     * Returns if the user is currently registered to attend the event.
     *
     * @return true for if they are attending and false for if they are not.
     */
    boolean isAttending();

    /**
     * Toggles whether or not the user is attending the event. Should update the
     * database as well.
     *
     * @param b
     */
    void setAttending(boolean b);


    void setTitle(String s);

    String getTitle();


    void setDescription(String s);

    String getDescription();


    void setStartTime(Time t);

    String getStartTimeString();

    void setEndTime(Time t);

    String getEndTimeString();


    void setMinA(int i);


    void setMaxA(int i);

    int getMaxA();

    String getTags();


    void setDate(Date d);

    String getDateString();


    void setCoords(double x, double y);

    double[] getCoords();

}
