package ui;


import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;

import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class CreditCardApp {
    private static final String JSON_STORE = "./data/accounts.txt";
    private CreditCard card1;
    private CreditCard card2;
    private CreditCard card3;
    private CreditCard targetCard;
    private ToDoCards cardList;

    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the credit application
    public CreditCardApp() throws FileNotFoundException, ParseException {
        input = new Scanner(System.in);
        cardList = new ToDoCards();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCreditCardApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCreditCardApp() throws ParseException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws ParseException {
        if (command.equals("f")) {
            doFindCard();
        } else if (command.equals("a")) {
            doAddCard();
        } else if (command.equals("t")) {
            doAddTransaction();
        } else if (command.equals("d")) {
            doSearchTransactionsBeforeGivenDate();
        } else if (command.equals("s")) {
            saveToDoCards();
        } else if (command.equals("l")) {
            loadToDoCards();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    private void doSearchTransactionsBeforeGivenDate() throws ParseException {
        findTargetCard();

        System.out.print("Enter a date(yyyy-mm-dd) to search: ");
        String date = input.next();

        TransactionList targetCardTransactionList = targetCard.getTransactionList();
        List<Transaction> resultCardTransactionList = null;

        resultCardTransactionList = targetCardTransactionList.transactionListBeforeGivenDate(date);

        System.out.println("Transaction records result is showing below:");
        printTransactionList(resultCardTransactionList);


    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        card1 = new CreditCard("1234 1234 4321 4321","Kay Sun",
                "2205 Lower Mall, Vancouver","(778)1231123", 500);
        card2 = new CreditCard("1231 3131 4342 1235","Janice Lee",
                "1002 Happy Garden, Vancouver","(778)9871153", 3000);
        card3 = new CreditCard("1783 3299 4992 9915","Tom Lu",
                "1188 Agronomy Road, Vancouver","(778)1133222", 10000);
        cardList = new ToDoCards();
        cardList.addCard(card1);
        cardList.addCard(card2);
        cardList.addCard(card3);

        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tf -> find a credit card");
        System.out.println("\ta -> add a new credit card");
        System.out.println("\tt -> add a transaction to a credit card");
        System.out.println("\td -> search for transactions before given date");
        System.out.println("\ts -> save card list to file");
        System.out.println("\tl -> load card list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doFindCard() {
        System.out.print("Enter account No.: ");
        int accountNo = input.nextInt();
        int count = 1;
        for (CreditCard eachCard : cardList.getCreditCardsList()) {
            if (eachCard.getAccountNo() == accountNo) {
                showCardDetails(eachCard);
                count++;

            } else if (count >= cardList.cardListLength()) {
                System.out.println("No such card exists!\n");
                System.out.println();
            }
        }
    }


   // EFFECTS: show the details of the credit card
    private void showCardDetails(CreditCard card) {
        System.out.println();
        System.out.println("Card details are showing below:");
        System.out.println("Account No.: " + card.getAccountNo());
        System.out.println("Credit Card No.: " + card.getCreditCardNo());
        System.out.println("Cardholder: " + card.getName());
        System.out.println("Address: " + card.getAddress());
        System.out.println("Phone No: " + card.getPhoneNo());
        System.out.println("Balance: $" + card.getBalance());
        System.out.println("Total Credit Limit: $" + card.getCreditLimit());
        System.out.println();
    }





    private void doAddCard() {
        System.out.print("Enter card number (16 digits with spaces): ");
        String cardNo = input.nextLine();
        cardNo += input.nextLine();
        System.out.print("Enter card holder name: ");
        String cardHolderName = input.nextLine();
        System.out.print("Enter address: ");
        String address = input.nextLine();
        System.out.print("Enter phone number: ");
        String phone = input.nextLine();
        System.out.print("Enter initial credit limit: $");
        int creditLimit = input.nextInt();
        System.out.println();

        System.out.print("The new card is set up successfully.");
        CreditCard newCard = new CreditCard(cardNo,cardHolderName,address,phone,creditLimit);
        cardList.addCard(newCard);
        showCardDetails(newCard);


        System.out.println();

    }



    // EFFECTS: adds a transaction to the transaction list of a credit card
    private void doAddTransaction() {
        findTargetCard();
        System.out.print("Enter transaction date (yyyy-MM-dd): ");
        String date = input.next();
        System.out.print("Enter transaction amount (with negative meaning paying credit card): ");
        double amount = input.nextDouble();


        Transaction newTransaction = new Transaction(date,amount);
        doUpdateTransactionList(targetCard,newTransaction);
    }


    public void doUpdateTransactionList(CreditCard targetCard, Transaction newTransaction) {
        if (targetCard.getBalance() + newTransaction.getAmount() <= targetCard.getCreditLimit()) {
            targetCard.addTransactionToCard(newTransaction);
            TransactionList newTransactionList = targetCard.getTransactionList();
            List<Transaction> resultTransactionList = newTransactionList.getTransactionList();

            targetCard.updateBalance(newTransaction);
            System.out.println("Transaction records of AccountNo." + targetCard.getAccountNo() + " are showing below:");
            printTransactionList(resultTransactionList);
            System.out.println("Balance now: $" + targetCard.getBalance());
            System.out.println();

        } else {
            System.out.println("Not enough credit limit left!");
            System.out.println();
        }
    }


    public void findTargetCard() {
        System.out.print("Enter account No.: ");
        int accountNo = input.nextInt();
        int count = 1;
        for (CreditCard eachCard : cardList.getCreditCardsList()) {
            if (eachCard.getAccountNo() == accountNo) {
                targetCard = eachCard;
                showCardDetails(eachCard);
                count++;

            } else if (count == cardList.cardListLength()) {
                System.out.println("No such card exists!\n");
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printTransactionList(List<Transaction> transactionList) {
//        System.out.println();
//        System.out.println("Transaction records are showing below:");
        for (Transaction eachTransaction: transactionList) {
            System.out.println("Date: " + eachTransaction.getDate() + "  Amount: $"
                    + eachTransaction.getAmount() + ";");
        }
        System.out.println();
    }

    // EFFECTS: save to-do cards to file
    private void saveToDoCards() {
        try {
            jsonWriter.open();
            jsonWriter.write(cardList);
            jsonWriter.close();
            System.out.println("Saved to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads to-do cards from file
    private void loadToDoCards() {
        try {
            cardList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
