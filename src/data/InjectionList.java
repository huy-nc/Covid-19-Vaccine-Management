package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tool.InputData;

/*
 * @author HuyNguyen
 */
public class InjectionList {

    private ArrayList<Injection> injectList = new ArrayList<>();
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Vaccine> vaccineList = new ArrayList<>();
    //==========================================================================

    public void addNewInjection() {
        String sID;
        String vID;
        String dayOfFirstInjection;
        String dayOfSecondInjection;
        String firstPlace;
        String secondPlace;
        String inJectID;
        int pos1;
        int pos2;
        int pos3;
        int pos4;
        String cont;
        do {

            do {
                inJectID = InputData.getId("Please Enter Injection ID(Ixxx): ", "The ID must be correct with the pattern (Ixxx)! Please re-enter.", "ID can't not empty!", "^I[0-9]{3}$");
                pos1 = checkIDInject(inJectID);
                if (pos1 != -1) {
                    System.out.println("The ID is already exist!" + " Please re-enter another one: ");
                }
            } while (pos1 != -1);
            do {
                System.out.println("=============== [StudentList] ===============");
                for (Student s : studentList) {
                    System.out.println(s);
                };
                sID = InputData.getId("Please Enter Student ID(Sxxx) In School: ", "The ID must be correct with the pattern (Sxxx)! Please re-enter.", "ID can't not empty!", "^S[0-9]{3}$");
                pos2 = checkStudentId(sID);
                pos4 = checkStudentInjectListID(sID);
                if (pos2 >= 0) {
                    System.out.println("This student completed 1 injection." + "Please Re-enter: ");
                } else if (pos4 != -1) {
                    System.out.println("This student is already exist. " + " Please re-enter onther one: ");
                }
            } while (pos2 >= 0 || pos4 != -1);
            do {
                System.out.println("=============== [VaccineList] ===============");
                for (Vaccine v : vaccineList) {
                    System.out.println(v);
                };
                vID = InputData.getId("Please Enter Vaccine ID(Vxxx): ", "The ID must be correct with the pattern (Vxxx)! Please re-enter.", "ID can't not empty!", "^V[0-9]{3}$");
                pos3 = checkVaccineId(vID);
                if (pos3 < 0) {
                   System.out.println("This Vaccine does not exist." + "Please re-enter: ");
                }
            } while (pos3 < 0);
            firstPlace = InputData.inputString("Enter the place of the first injection: ", "Place can't not empty!");
            secondPlace = null;
            while (true) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                dayOfFirstInjection = InputData.inputString("Input first date (Format: dd/MM/yyyy): ",
                        "The format date is dd/MM/yyyy!!");
                df.setLenient(false);
                try {
                    df.parse(dayOfFirstInjection);
                    break;
                } catch (ParseException e) {
                    System.out.println("INVALID DATE!!!");
                }
            }
            dayOfSecondInjection = null;
            injectList.add(new Injection(sID, vID, dayOfFirstInjection, dayOfSecondInjection, firstPlace, secondPlace, inJectID));
            System.out.println("Add succesfully!");
            cont = InputData.getId("Press Y to adding Or N back to menu: ", "ENTER AGAIN PLEASE", "can't not empty", "^[Y|N]$");
        } while (cont.equalsIgnoreCase("Y"));
        System.out.printf("%-10s|%-5s|%-5s|%-11s|%-11s|%-15s|%-15s|",
                "Inject ID", "sID", "vID", "FirstDate", "SecondDate", "FirstPlace", "SecondPlace");
        System.out.println("");
        for (Injection injection : injectList) {
            injection.show();
        }
    }
    //==========================================================================

    public void updateStudentInjection() {
        String inJectID;
        String dayOfSecondInjection;
        String secondPlace;
        int pos2 = -1;
        Boolean pos5 = true;

        do {
            inJectID = InputData.getId("Please Enter Injection ID(Ixxx): ", "The ID must be correct with the pattern (Ixxx)! Please re-enter.", "ID can't not empty", "^I[0-9]{3}$");
            pos2 = checkIDInject(inJectID);
            if (pos2 == -1) {
                System.out.println("This student does not exist in this school" + "Please re-enter: ");
            }
        } while (pos2 == -1);
        if (injectList.get(pos2).getDayOfSecondInjection() != null) {
            System.out.println("This student completed 2 injection");
        } else {
            System.out.println("This student ready to update");
            secondPlace = InputData.inputString("Enter Second Place: ", "Place can't not empty!");
            do{    
                while (true) {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    dayOfSecondInjection = InputData.inputString("Input second date (Format: dd/MM/yyyy): ",
                        "The format date is dd/MM/yyyy!!");
                    df.setLenient(false);
                    try {
                        df.parse(dayOfSecondInjection);
                        break;
                    } catch (ParseException e) {
                        System.out.println("INVALID DATE!!!");
                    }
                }
                for (Injection injection : injectList) {
                    if (injection.getInJectID().equalsIgnoreCase(inJectID)) {
                        String date1 = injection.getDayOfFirstInjection();
                        System.out.println(date1);
                        if (checkDay(date1, dayOfSecondInjection) > 28 && checkDay(date1, dayOfSecondInjection) < 84) {
                            injection.setSecondPlace(secondPlace);
                            injection.setDayOfSecondInjection(dayOfSecondInjection);
                            System.out.println("Succesfully!");
                            pos5 = true;
                        } else {
                            System.out.println("Must Over 4 Weeks And Less Than 12 Weeks" + " Update Fail");
                            pos5 = false;
                        } 
                    }
                }
            } while (pos5 != true);        
        }
    }
    //==========================================================================

    public void searchListByStudentID() {
        String id;
        int pos1;
        int pos2;
        id = InputData.inputString("Input Student ID to find: ", "Not found!");
        pos2 = checkStudentInjectListID(id);
        if (pos2 == -1) {
            System.out.println("Not Found");
        } else {
            System.out.printf("%-5s|%-5s|%-20s|%-11s|%-11s|%-10s|%-11s|%-10s|",
                    "InjID", "sID", "StudentName", "VaccineName", "FirstPlace", "FirstDate", "SecondPlace", "SecondDate");
            System.out.println("");
            displayInjectList(pos2);
        }
    }
    //==========================================================================

    public void deleteInjectList() {
        String id;
        int pos;
        String cont;
        id = InputData.inputString("Input ID injectTion want to delete: ", "ID can't not find");
        pos = checkIDInject(id);
        if (pos == -1) {
            System.out.println("Id not found");
        } else {
            System.out.printf("%-5s|%-5s|%-20s|%-11s|%-11s|%-10s|%-11s|%-10s|",
                    "InjID", "sID", "StudentName", "VaccineName", "FirstPlace", "FirstDate", "SecondPlace", "SecondDate");
            System.out.println("");
            displayInjectList(pos);
            System.out.println("This is injection you want to delete");
            cont = InputData.getId("Press Y to delete Or N back to menu: ", "ENTER AGAIN PLEASE", "can't not empty", "^[Y|N]$");
            if (cont.equalsIgnoreCase("Y")) {
                injectList.remove(pos);
                System.out.println("Remove succesfully!");
                display();
            } else {
                System.out.println("Remove fail!");
                display();
            }
        }
    }
    //==========================================================================

    public void display() {
        if (injectList.isEmpty()) {
            System.out.println("List empty!");
        } else {
            System.out.printf("%-5s|%-5s|%-20s|%-11s|%-11s|%-10s|%-11s|%-10s|",
                    "InjID", "sID", "StudentName", "VaccineName", "FirstPlace", "FirstDate", "SecondPlace", "SecondDate");
            System.out.println("");
            for (int i = 0; i < injectList.size(); i++) {
                displayInjectList(i);
            }
        }
    }

    public void printStudent() {
        System.out.printf("%-4s|%-10s", "SID", "Name");
        System.out.println("");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private void displayInjectList(int i) {
        Injection inj = injectList.get(i);
        String show = String.format("%-5s|%-5s|%-20s|%-11s|%-11s|%-10s|%-11s|%-10s|",
                inj.getInJectID(),
                inj.getsID(),
                studentList.get(checkStudentId(inj.getsID())).getNameStudent(),
                vaccineList.get(checkVaccineId(inj.getvID())).getVaccineName(),
                inj.getFirstPlace(),
                inj.getDayOfFirstInjection(),
                inj.getSecondPlace(),
                inj.getDayOfSecondInjection());
        System.out.println(show);
    }
    //==========================================================================

    public long checkDay(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        long days = 0;
        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long getTime = d2.getTime() - d1.getTime();
            days = getTime / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            System.out.println(e);
        }
        return days;
    }

    public int checkIDInject(String id) {
        if (injectList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < injectList.size(); i++) {
            if (injectList.get(i).inJectID.equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkStudentId(String id) {
        if (studentList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).studentID.equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkStudentInjectListID(String id) {
        if (injectList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < injectList.size(); i++) {
            if (injectList.get(i).sID.equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int checkVaccineId(String id) {
        if (vaccineList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).vaccineId.equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    //==========================================================================

    public void writeStudentFile(String file) {
        ArrayList<Student> studentsList = new ArrayList<>();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            studentList.add(new Student("S110", "Hưng"));
            studentList.add(new Student("S111", "Kiên"));
            studentList.add(new Student("S112", "Tâm"));
            studentList.add(new Student("S113", "Tài"));
            studentList.add(new Student("S114", "Tuấn"));
            studentList.add(new Student("S115", "Sơn"));
            studentList.add(new Student("S116", "Tùng"));
            studentList.add(new Student("S117", "Châu"));
            studentList.add(new Student("S118", "Giang"));
            studentList.add(new Student("S119", "Huy"));
            oos.writeObject(studentList);
            fos.close();
            oos.close();
        } catch (IOException ex) {
            System.out.println("Save Fail: " + ex);
        }
    }

    public void readStudentFile(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Student> studentList = (ArrayList<Student>) ois.readObject();
            this.studentList = studentList;
            ois.close();
            fis.close();
        } catch (IOException io) {
            System.out.println("List is empty!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        }
    }
    
    public void writeVaccineFile(String file) {
        ArrayList<Vaccine> vaccineList = new ArrayList<>();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            vaccineList.add(new Vaccine("V001", "Astra"));
            vaccineList.add(new Vaccine("V002", "Vero Cell"));
            vaccineList.add(new Vaccine("V003", "Pfizer"));
            vaccineList.add(new Vaccine("V004", "Mordena"));
            vaccineList.add(new Vaccine("V005", "Nanocovax"));
            oos.writeObject(vaccineList);
            fos.close();
            oos.close();
        } catch (IOException ex) {
            System.out.println("Save Fail: " + ex);
        }
    }
    
    public void readVaccineFile(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Vaccine> vaccineList = (ArrayList<Vaccine>) ois.readObject();
            this.vaccineList = vaccineList;
            ois.close();
            fis.close();
        } catch (IOException io) {
            System.out.println("List is empty!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        }
    }

    public void writeInjectFile(String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(injectList);
            fos.close();
            oos.close();
        } catch (IOException ex) {
            System.out.println("Save Fail: " + ex);
        }
    }

    public void readInjectFile(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Injection> injectList = (ArrayList<Injection>) ois.readObject();
            this.injectList = injectList;
            System.out.printf("%-5s|%-5s|%-20s|%-11s|%-11s|%-10s|%-11s|%-10s|",
                    "InjID", "sID", "StudentName", "VaccineName", "FirstPlace", "FirstDate", "SecondPlace", "SecondDate");
            System.out.println("");
            for (int i = 0; i < injectList.size(); i++) {
                displayInjectList(i);
            }
            ois.close();
            fis.close();
        } catch (IOException io) {
            System.out.println("List is empty!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        }
    }
}
