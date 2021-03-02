package ui;

import java.io.FileNotFoundException;
import java.text.ParseException;





public class Main {
    public static void main(String[] args) throws ParseException {
        try {
            new CreditCardApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
