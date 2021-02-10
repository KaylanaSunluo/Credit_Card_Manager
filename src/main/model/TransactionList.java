package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


// represents a list of transactions happened so far
public class TransactionList extends LinkedList<Transaction> {
    private LinkedList<Transaction> transactionList;

    // Construct an empty collection of transactions
    public TransactionList() {
        transactionList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new transaction to the first of transaction list happened
    public void insertTransaction(Transaction t) {
        transactionList.addFirst(t);
    }

    // EFFECTS: returns the number of transactions in the list
    public int transactionNum() {
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
        LinkedList transactionListResult = new LinkedList<Transaction>();

        for (Transaction eachTransaction : transactionList) {
            if (eachTransaction.beforeDate(givenDate)) {
                transactionListResult.add(eachTransaction);
            }
        }
        return transactionListResult;
    }
}
