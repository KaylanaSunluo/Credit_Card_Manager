package model;

// Represents a credit card account having an account number, card number, card holder
// name, address, phone number, credit limit (in dollars), balance (in dollars) and
// a list of transactions (in dollars)

import org.json.JSONObject;
import persistence.Writable;

public class CreditCard implements Writable {
    private static int initial = 1;
    private static int nextAccountNo = 1;    // tracks number of next account created
    private int accountNo;                   // account number
    private String cardNo;                   // card number
    private String name;                     // cardholder name of card holder
    private String address;                  // the address of card holder
    private String phoneNo;                  // the phone number of card holder
    private int creditLimit;                 // credit limit
    private double balance;                  // the current balance of the card account,with
                                             // positive representing the money owed to the bank and
                                             // negative representing the money saved in the credit card

    private TransactionList transactionList;  // the list of past transactions



    /*
     * REQUIRES: cardNumber consists of 16 digits, with the format "0000 0000 0000 0000";
     *           phone is in form: (123) 4567890;
     *           initialCreditLimit >= 1000
     * EFFECTS: constructs a credit card account with account number, card number, card holder name,
     *          card holder address, card holder phone number, credit limit, zero balance and
     *          an empty transaction list.
     *
     *          account number is a positive integer not assigned to any other credit card account;
     *          cardNo is in the form "xxxx xxxx xxxx xxxx"; name on credit card account is set to
     *          cardHolderName; address on credit card account is set to cardHolderAddress; phone
     *          number on credit card account is set to phone; credit limit is set to initialCreditLimit;
     *          balance on credit card account is set to 0; transaction list is set to a empty list.
     */

    public CreditCard(String cardNumber, String cardHolderName, String cardHolderAddress,
                      String phone, int initialCreditLimit) {
        this.accountNo = nextAccountNo++;
        this.cardNo = cardNumber;
        this.name = cardHolderName;
        this.address = cardHolderAddress;
        this.phoneNo = phone;
        this.creditLimit = initialCreditLimit;
        this.balance = 0;
        this.transactionList = new TransactionList();
    }

    // EFFECTS: returns the account number of the credit card
    public int getAccountNo() {
        return accountNo;
    }

    // EFFECTS: returns the credit card number
    public String getCreditCardNo() {
        return cardNo;
    }

    // EFFECTS: returns the card holder name of the credit card
    public String getName() {
        return name;
    }

    // EFFECTS: returns the card holder address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns card holder phone number
    public String getPhoneNo() {
        return phoneNo;
    }

    // EFFECTS: returns the balance of the credit card
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns the credit of the credit card
    public double getCreditLimit() {
        return creditLimit;
    }

    // EFFECTS: returns the transaction list of the credit card
    public TransactionList getTransactionList() {
        return transactionList;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to balance and returns the updated balance
    public double updateBalance(Transaction transaction) {
        balance = balance + transaction.getAmount();
        return balance;
    }

    // MODIFIES: this
    // EFFECTS: adds a transaction to the credit card transaction list
    public void addTransactionToCard(Transaction transaction) {
        transactionList.insertTransactionToTransactionList(transaction);
    }

    //REQUIRES: newAccountNo > 0
    //MODIFIES: this
    //EFFECTS: changes the account number to the new number
    public void changeAccountNo(int newAccountNo) {
        this.accountNo = newAccountNo;
    }

    //REQUIRES: newBalance > 0
    //MODIFIES: this
    //EFFECTS: changes the balance to the given new balance
    public void changeBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void changeTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("accountNo", initial++);
        json.put("cardNo", cardNo);
        json.put("cardHolderName", name);
        json.put("address", address);
        json.put("phoneNo", phoneNo);
        json.put("creditLimit", creditLimit);
        json.put("balance", balance);

        json.append("cardTransactionList",transactionList.toJson());


        return json;
    }

}



