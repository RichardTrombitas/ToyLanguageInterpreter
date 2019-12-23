package View;

import Controller.Controller;
import Model.*;
import Model.Data.*;
import Model.Expressions.*;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Repository.IRepository;
import Repository.Repository;
import View.GUI.PrgSelectionCtrl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class Interpreter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrgSelectionCtrl ctrl = new PrgSelectionCtrl();
        ctrl.display();

        Parent root = FXMLLoader.load(getClass().getResource("GUI/Main.fxml"));
        primaryStage.setTitle("Toy Language Interpreter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
