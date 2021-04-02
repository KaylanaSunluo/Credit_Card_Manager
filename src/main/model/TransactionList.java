package model;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


// represents a list of transactions happened so far
public class TransactionList extends ArrayList implements RecordList {

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
    @Override
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
    @Override
    public List<Transaction> getList() {
        return transactionList;
    }


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

}
