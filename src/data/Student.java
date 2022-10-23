package data;

import java.io.Serializable;

/*
 * @author HuyNguyen
 */
public class Student implements Serializable {

    String studentID;
    String nameStudent;

    public Student(String studentID, String nameStudent) {
        this.studentID = studentID;
        this.nameStudent = nameStudent;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    @Override
    public String toString() {
        return studentID + "|" + nameStudent;
    }

    public void show() {
        String show = String.format("%-10s| %-30s", studentID, nameStudent);
        System.out.println(show);
    }

}
