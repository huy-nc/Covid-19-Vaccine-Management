package data;

import java.io.Serializable;

/*
 * @author HuyNguyen
 */
public class Vaccine implements Serializable {

    String vaccineId;
    String vaccineName;

    public Vaccine(String vaccineId, String vaccineName) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return vaccineId + " |" + vaccineName;
    }

    public void print() {
        String show = String.format("%-3s|%-30s", vaccineId, vaccineName);
    }

}
