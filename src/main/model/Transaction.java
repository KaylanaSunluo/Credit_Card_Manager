package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// represents a transaction with date and amount of money;
// The date is when the transaction happened;
// The amount can be positive meaning the total amount of money owed to the bank,
// and negative representing the money pay the credit card

public class Transaction {
    private String date;
    private double amount;

    // REQUIRES: date is in the form "yyyy/MM/dd" and no later then today;
    //           amount != 0
    // MODIFIES: this
    // EFFECTS: constructs a transaction with an associated date and amount,
    // with positive meaning customers' borrowing and negative meaning credit card payment
    public Transaction(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }


    // EFFECTS: returns the date of the transaction
    public String getDate() {
        return date;
    }

    // EFFECTS: returns the amount of the transaction
    public double getAmount() {
        return amount;
    }



    // REQUIRES: given date is in the form "yyyy/MM/dd"
    // EFFECTS: returns true if the transaction happens before the given date
    public boolean beforeDate(String givenDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(date);
        Date date2 = sdf.parse(givenDate);
        return date1.before(date2);
    }

}



