package View.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.io.IOException;

public class PrgSelectionCtrl {

    public static void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Program selection");
        Parent root = FXMLLoader.load(PrgSelectionCtrl.class.getResource("PrgSelection.fxml"));
        window.setScene(new Scene(root));
        window.showAndWait();


    }

}


