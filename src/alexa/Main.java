package alexa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    private static Label label_ascii, label_binary, label_hex;
    private static TextField input;
    private static Button button_encode;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int WIDTH = 286;

        Parent root = FXMLLoader.load(Main.class.getResource("ui.fxml"));

        primaryStage.setTitle("ASCII Encoder");
        primaryStage.setScene(new Scene(root, WIDTH, 124));
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.show();

        label_ascii = (Label) root.lookup("#label_ascii");
        label_binary = (Label) root.lookup("#label_binary");
        label_hex = (Label) root.lookup("#label_hex");

        input = (TextField) root.lookup("#input_encode");
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            doEncode(newValue);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void doEncode(String input){

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
