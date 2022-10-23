package tool;

import java.util.ArrayList;

/*
 * @author HuyNguyen
 */
public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("Not thing to print");
            return;
        }
        System.out.println("--------------- [" + menuTitle + "] ---------------");
        for (String o : optionList) {
            System.out.println(o);
        }
    }

    public int getChoice() {
        int maxOption = optionList.size();
        String msg = "Choose[1 to " + maxOption + "]";
        String err = "WRONG... 1 TO " + maxOption;
        return InputData.inputInt(msg, err, 1, maxOption);
    }

}
