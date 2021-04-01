package persistence;


import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;
import model.exceptions.FormatIncorrectException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/* Represents a reader that reads to-do cards from JSON data stored in file
 * Citation: Code obtained from JsonSerializationDemo
 * Retrieved from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads to-do cards from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoCards read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        try {
            return parseToDoCards(jsonObject);
        } catch (FormatIncorrectException formatIncorrectException) {
            return new ToDoCards();
        }
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses to-do cards from JSON object and returns it
    private ToDoCards parseToDoCards(JSONObject jsonObject) throws FormatIncorrectException {
        ToDoCards cardList = new ToDoCards();
        addCards(cardList, jsonObject);
        return cardList;
    }

    // MODIFIES: cardList
    // EFFECTS: parses cards from JSON object and adds them to to-do cards
    private void addCards(ToDoCards cardList, JSONObject jsonObject) throws FormatIncorrectException {
        JSONArray jsonArray = jsonObject.getJSONArray("creditCardList");
        for (Object json : jsonArray) {
            JSONObject nextCreditCard = (JSONObject) json;
            addCreditCard(cardList, nextCreditCard);
        }
    }

    // MODIFIES: cardList
    // EFFECTS: parses card from JSON object and adds it to to-do cards
    private void addCreditCard(ToDoCards cardList, JSONObject jsonObject) throws JSONException,
            FormatIncorrectException {
        int accountNo = jsonObject.getInt("accountNo");
        String cardNo = jsonObject.getString("cardNo");
        String cardHolderName = jsonObject.getString("cardHolderName");
        String address = jsonObject.getString("address");
        String phoneNo = jsonObject.getString("phoneNo");
        int creditLimit = jsonObject.getInt("creditLimit");
        double balance = jsonObject.getDouble("balance");

        CreditCard card = new CreditCard(cardNo, cardHolderName, address, phoneNo, creditLimit);

        card.changeAccountNo(accountNo);
        card.changeBalance(balance);


        try {
            TransactionList transactionList = parseTransactionList(jsonObject.getJSONArray("transactionList"));
            card.changeTransactionList(transactionList);
        } catch (JSONException e) {
            //pass
        }
        cardList.addCard(card);

    }




    // EFFECTS: parses transactionList from JSON object and returns it
    private TransactionList parseTransactionList(JSONArray jsonArray) {
        TransactionList transactionList = new TransactionList();
        addTransactions(transactionList, jsonArray);
        return transactionList;
    }

    // MODIFIES: transactionList
    // EFFECTS: parses transactions from JSON object and adds them to transactionList
    private JSONArray addTransactions(TransactionList transactionList, JSONArray jsonArray) {

        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(transactionList, nextTransaction);
        }
        return jsonArray;

    }

    // MODIFIES: transactionList
    // EFFECTS: parses transaction from JSON object and adds it to transactionList
    private void addTransaction(TransactionList transactionList, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        double amount = jsonObject.getDouble("amount");
        Transaction transaction = new Transaction(date,amount);

        transactionList.insertTransactionToTransactionList(transaction);
    }

}
