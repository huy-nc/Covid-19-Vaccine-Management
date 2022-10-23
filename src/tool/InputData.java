package tool;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/*
 * @author HuyNguyen
 */
public class InputData {
    private static Scanner sc = new Scanner(System.in);

    public static int inputInt(String msg, String err) {
        int n;
        while (true) {
            try {
                System.out.println(msg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }

    public static int inputInt(String msg, String err, int min, int max) {

        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        int n;
        while (true) {
            try {
                System.out.println(msg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }

    public static double inputDouble(String msg, String err, double min, double max) {

        if (min > max) {
            double tmp = min;
            min = max;
            max = tmp;
        }
        double n;
        while (true) {
            try {
                System.out.println(msg);
                n = Double.parseDouble(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(err);
            }
        }
    }

    public static String inputString(String msg, String err) {
        String id;
        while (true) {
            System.out.println(msg);
            id = sc.nextLine().trim().toLowerCase();
            if (id.length() == 0 || id.isEmpty()) {
                System.err.println(err);
            } else {
                return id;
            }
        }
    }

    public static String getId(String msg, String err, String emp, String pattern) {
        boolean match;
        String id;
        while (true) {
            System.out.println(msg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(pattern);
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(emp);
            } else if (match == false) {
                System.err.println(err);
            } else {
                return id;
            }
        }
    }

    public static int getPositiveInteger(String msg, String err) {
        int number;
        do {
            System.out.println(msg);
            while (!sc.hasNextInt()) {
                System.err.println(err);
                sc.next();
            }
            number = sc.nextInt();
        } while (number < 1);
        return number;
    }

    public static double getPositiveDouble(String msg, String err) {
        double number;
        do {
            System.out.println(msg);
            while (!sc.hasNextDouble()) {
                System.err.println(err);
                sc.next();
            }
            number = Double.parseDouble(sc.nextLine());
        } while (number < 1);
        return number;
    }

    public static double checkInputDouble(String msg, String err) {
        boolean check = true;
        double result = 0;
        do {
            try {
                System.out.print(msg);
                result = Double.parseDouble(sc.nextLine().trim());
                if (result <= 0) {
                    System.out.println("Number input must be greater than 0!");
                    check = true;
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        } while (check);
        return result;
    }
}