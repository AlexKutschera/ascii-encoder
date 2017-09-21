package alexa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AsciiEncoder extends Application {

    private static Label label_ascii, label_binary, label_hex;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ascii-encoder.fxml"));

        label_ascii = (Label) root.lookup("#label_ascii");
        label_binary = (Label) root.lookup("#label_binary");
        label_hex = (Label) root.lookup("#label_hex");

        TextField input = (TextField) root.lookup("#input_encode");

        Button encode = (Button) root.lookup("#button_encode");
        encode.setOnMouseClicked(event -> {
            doEncode(input.getText());
        });

        primaryStage.setTitle("ASCII Encoder");
        primaryStage.setScene(new Scene(root, 264, 118));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void doEncode(String input){
        if (input.toCharArray().length > 0){
            char character = input.toCharArray()[0];

            String ascii = "";
            String binary = "";
            String hex = "";

            ascii += character + ": ";
            ascii += (int) character;

            binary += character + ": ";
            binary += Integer.toBinaryString(character);

            hex += character + ": ";
            hex += Integer.toHexString(character);

            ascii.trim();

            label_ascii.setText(ascii);
            label_binary.setText(binary);
            label_hex.setText(hex);
        }

    }
}
