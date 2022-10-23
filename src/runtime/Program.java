package runtime;

import data.InjectionList;
import tool.Menu;

/*
 * @author HuyNguyen
 */
public class Program {

    public static void main(String[] args) {
        Menu mnu = new Menu("Injection List");
        mnu.addNewOption("1. Show information all students have been injected");
        mnu.addNewOption("2. Add student's vaccine injection information");
        mnu.addNewOption("3. Updating information of students' vaccine injection");
        mnu.addNewOption("4. Delete student vaccine injection information");
        mnu.addNewOption("5. Search for injection information by studentID");
        mnu.addNewOption("6. Save to file");
        mnu.addNewOption("7. Quit");

        InjectionList in = new InjectionList();
        in.readStudentFile("docx\\Student.bin");
        in.readVaccineFile("docx\\vaccine.bin");
        in.readInjectFile("docx\\InjectList.dat");
        int choice;

        do {
            mnu.printMenu();
            choice = mnu.getChoice();
            switch (choice) {
                case 1:
                    in.printStudent();
                    break;
                case 2:
                    in.addNewInjection();
                    break;
                case 3:
                    in.updateStudentInjection();
                    break;
                case 4:
                    in.deleteInjectList();
                    break;
                case 5:
                    in.searchListByStudentID();
                    break;
                case 6:
                    in.writeInjectFile("docx\\InjectList.dat");
                    break;
            }
        } while (choice != 7);
    }
}
