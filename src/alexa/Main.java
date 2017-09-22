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
    private static Button button_store;

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
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            encode(newValue);
        });

        button_store = (Button) root.lookup("#button_store");
        button_store.setOnMouseClicked(event -> {
            store(input.getText());
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void store(String input){
        String ascii = "ASCII: ";
        String binary = "BINÄR: ";
        String hex = "HEXADEZIMAL: ";

        for (char character:input.toCharArray()) {
            ascii += (int) character;
            ascii += " ";

            binary += Integer.toBinaryString(character);
            binary += " ";

            hex += Integer.toHexString(character);
            hex += " ";
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

        String ascii = "";
        String binary = "";
        String hex = "";

        for (char character:input.toCharArray()) {
            ascii += character + ": ";
            ascii += (int) character;
            ascii += "\r\n";

            binary += character + ": ";
            binary += Integer.toBinaryString(character);
            binary += "\r\n";

            hex += character + ": ";
            hex += Integer.toHexString(character);
            hex += "\r\n";
        }

        label_ascii.setText(ascii.trim());
        label_binary.setText(binary.trim());
        label_hex.setText(hex.trim());

    }
}
