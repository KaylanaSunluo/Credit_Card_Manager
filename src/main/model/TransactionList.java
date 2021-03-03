package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.nio.file.Watchable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


// represents a list of transactions happened so far
public class TransactionList implements Writable {

    private Transaction transaction;
    private List<Transaction> transactionList;

    // Construct an empty collection of transactions
    public TransactionList() {
        transactionList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new transaction to the first of transaction list happened
    public void insertTransactionToTransactionList(Transaction transaction) {
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

    // EFFECTS: returns the transaction list as a List<Transaction>
    public List<Transaction> getTransactionList() {
        return transactionList;
    }


    // REQUIRES: given date is in the form "yyyy/MM/dd"
    // MODIFIES: this
    // EFFECTS: returns a transaction list containing transactions happened before the given date
    public List<Transaction> transactionListBeforeGivenDate(String givenDate) throws ParseException {
        List<Transaction> transactionListResult = new ArrayList<>();

        for (Transaction eachTransaction : transactionList) {
            if (eachTransaction.beforeDate(givenDate)) {
                transactionListResult.add(eachTransaction);
            }
        }
        return transactionListResult;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        for (Transaction t: transactionList) {
            json.append("transactionList", t.toJson());
        }
        return json;
    }


//    public JSONObject toJson() {
//        JSONObject json = new JSONObject();
//        json.put("transactionList", transactionListToJson());
//        return json;
//    }

    // EFFECTS: returns things in this to-do cards as a JSON array
//    private JSONArray transactionListToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (Transaction t : transactionList) {
//            jsonArray.put(t.toJson());
//        }
//        return jsonArray;
//    }

}
