package View.GUI;

import Controller.Controller;
import Model.Data.IHeap;
import Model.PrgState;
import Model.Values.Value;
import Repository.IRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainCtrl implements Initializable {

    public static Controller activePrg;
    @FXML private ListView<Integer> prgIDsListView;
    @FXML private TextField nrOfPrgStatesTextField;
    @FXML private TableView<> symTableView;
    @FXML private ListView<String> exeStackListView;
    @FXML private ListView<String> fileTableListView;
    @FXML private ListView<String> outputListView;
    @FXML private TableView<> heapTableView;
    @FXML private Button runOneStepButton;

    @FXML
    private void runOneStepButtonClicked() {

    }

    public void showData() {
        IRepository repo = activePrg.getRepository();
        List<PrgState> prgList = repo.getPrgList();
        IHeap heap = prgList.get(0).getHeap();

        for(PrgState prg : prgList) {
            prgIDsListView.getItems().add(prg.getThreadID());
        }

        nrOfPrgStatesTextField.setText("Nr. of PrgStates: " + Integer.toString(prgList.size()));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
    }
}
