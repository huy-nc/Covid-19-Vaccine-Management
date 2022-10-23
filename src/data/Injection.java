package data;

import java.io.Serializable;

/*
 * @author HuyNguyen
 */
public class Injection implements Serializable {

    String sID;
    String vID;
    String dayOfFirstInjection;
    String dayOfSecondInjection;
    String firstPlace;
    String secondPlace;
    String inJectID;

    public Injection(String sID, String vID, String dayOfFirstInjection, String dayOfSecondInjection, String firstPlace, String secondPlace, String inJectID) {
        this.inJectID = inJectID;
        this.sID = sID;
        this.vID = vID;
        this.dayOfFirstInjection = dayOfFirstInjection;
        this.dayOfSecondInjection = dayOfSecondInjection;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;

    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getvID() {
        return vID;
    }

    public void setvID(String vID) {
        this.vID = vID;
    }

    public String getDayOfFirstInjection() {
        return dayOfFirstInjection;
    }

    public void setDayOfFirstInjection(String dayOfFirstInjection) {
        this.dayOfFirstInjection = dayOfFirstInjection;
    }

    public String getDayOfSecondInjection() {
        return dayOfSecondInjection;
    }

    public void setDayOfSecondInjection(String dayOfSecondInjection) {
        this.dayOfSecondInjection = dayOfSecondInjection;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getInJectID() {
        return inJectID;
    }

    public void setInJectID(String inJectID) {
        this.inJectID = inJectID;
    }

    @Override
    public String toString() {
        return inJectID + "|" + sID + "|" + vID + "|" + dayOfFirstInjection + "|" + dayOfSecondInjection + "|" + firstPlace + "|" + secondPlace;
    }

    public void show() {
        String show = String.format("%-10s|%-5s|%-5s|%-11s|%-11s|%-15s|%-15s|", inJectID, sID, vID, dayOfFirstInjection, dayOfSecondInjection, firstPlace, secondPlace);
        System.out.println(show);
    }

}
