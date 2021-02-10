package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


// represents a list of transactions happened so far
public class TransactionList {

    private Transaction transaction;
    private List<Transaction> transactionList;

    // Construct an empty collection of transactions
    public TransactionList() {
        transactionList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new transaction to the first of transaction list happened
    public void insertTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    // EFFECTS: returns the number of transactions in the list
    public int length() {
        return transactionList.size();
    }

    // EFFECTS: returns the amount of the transactions in the list
    public double transactionAmount() {
        double sum = 0;
        for (Transaction eachTransaction : transactionList) {
            sum = sum + eachTransaction.getAmount();
        }
        return sum;
    }



    // REQUIRES: given date is in the form "yyyy/MM/dd"
    // MODIFIES: this
    // EFFECTS: returns a transaction list containing transactions happened before the given date
    public LinkedList<Transaction> transactionListBeforeGivenDate(String givenDate) throws ParseException {
        LinkedList<Transaction> transactionListResult;
        transactionListResult = new LinkedList<>();

        for (Transaction eachTransaction : transactionList) {
            if (eachTransaction.beforeDate(givenDate)) {
                transactionListResult.add(eachTransaction);
            }
        }
        return transactionListResult;
    }
}
