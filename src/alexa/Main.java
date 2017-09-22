package alexa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    private static Label label_ascii, label_binary, label_hex;
    private static TextField input;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int WIDTH = 346;

        Parent root = FXMLLoader.load(Main.class.getResource("ui.fxml"));

        primaryStage.setTitle("ASCII Encoder");
        primaryStage.setScene(new Scene(root, WIDTH, 136));
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.show();

        label_ascii = (Label) root.lookup("#label_ascii");
        label_binary = (Label) root.lookup("#label_binary");
        label_hex = (Label) root.lookup("#label_hex");

        input = (TextField) root.lookup("#input_encode");
        input.textProperty().addListener((observable, oldValue, newValue) -> encode(newValue));

        Button button_store = (Button) root.lookup("#button_store");
        button_store.setOnMouseClicked(event -> store(input.getText()));

    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void store(String input){
        StringBuilder ascii = new StringBuilder("ASCII: ");
        StringBuilder binary = new StringBuilder("BINÄR: ");
        StringBuilder hex = new StringBuilder("HEXADEZIMAL: ");

        for (char character:input.toCharArray()) {
            ascii.append((int) character);
            ascii.append(" ");

            binary.append(Integer.toBinaryString(character));
            binary.append(" ");

            hex.append(Integer.toHexString(character));
            hex.append(" ");
        }

        try {
            DateFormat format = new SimpleDateFormat("ddMMyyyy_HHmmss");
            PrintWriter writer = new PrintWriter("ascii-encoded_"+format.format(new Date())+".txt");
            writer.println("ORIGINAL: "+input);
            writer.println(ascii);
            writer.println(binary);
            writer.println(hex);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void encode(String input){

        StringBuilder ascii = new StringBuilder();
        StringBuilder binary = new StringBuilder();
        StringBuilder hex = new StringBuilder();

        for (char character:input.toCharArray()) {
            ascii.append(character).append(": ");
            ascii.append((int) character);
            ascii.append("\r\n");

            binary.append(character).append(": ");
            binary.append(Integer.toBinaryString(character));
            binary.append("\r\n");

            hex.append(character).append(": ");
            hex.append(Integer.toHexString(character));
            hex.append("\r\n");
        }

        label_ascii.setText(ascii.toString().trim());
        label_binary.setText(binary.toString().trim());
        label_hex.setText(hex.toString().trim());

    }
}
