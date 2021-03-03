package persistence;

import model.CreditCard;
import model.ToDoCards;
import model.Transaction;
import model.TransactionList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderTransactionList {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderTransactionList(String source) {
        this.source = source;
    }

    // EFFECTS: reads to-do cards from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TransactionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTransactionList(jsonObject);
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
    private TransactionList parseTransactionList(JSONObject jsonObject) {
        TransactionList transactionList = new TransactionList();
        addTransactions(transactionList, jsonObject);
        return transactionList;
    }

    // MODIFIES: cardList
    // EFFECTS: parses cards from JSON object and adds them to to-do cards
    private void addTransactions(TransactionList transactionList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("transactionList");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(transactionList, nextTransaction);
        }
    }

    // MODIFIES: cardList
    // EFFECTS: parses card from JSON object and adds it to to-do cards
    private void addTransaction(TransactionList transactionList, JSONObject jsonObject) {

        String date = jsonObject.getString("date");
        double amount = jsonObject.getDouble("amount");

        Transaction transaction = new Transaction(date, amount);
        transactionList.insertTransactionToTransactionList(transaction);
    }
}
