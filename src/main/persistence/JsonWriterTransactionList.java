package persistence;

import model.ToDoCards;
import model.TransactionList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriterTransactionList {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterTransactionList(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of to-do cards to file
    public void write(TransactionList transactionList) {
        JSONObject json = transactionList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
