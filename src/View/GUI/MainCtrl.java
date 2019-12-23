package View.GUI;

import Controller.Controller;
import Model.Data.IFileTable;
import Model.Data.IHeap;
import Model.Data.IOutputList;
import Model.PrgState;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class MainCtrl implements Initializable {

    public static Controller activePrg;
    @FXML private ListView<Integer> prgIDsListView;
    @FXML private TextField nrOfPrgStatesTextField;
    @FXML private TableView<SymTblEntry> symTableView;
    @FXML private TableColumn<HeapEntry, Integer> symTblNameColumn;
    @FXML private TableColumn<HeapEntry, Integer> symTblValueColumn;
    @FXML private ListView<String> exeStackListView;
    @FXML private ListView<StringValue> fileTableListView;
    @FXML private ListView<Value> outputListView;
    @FXML private TableView<HeapEntry> heapTableView;
    @FXML private TableColumn<HeapEntry, Integer> heapAddressColumn;
    @FXML private TableColumn<HeapEntry, Value> heapValueColumn;
    @FXML private Button runOneStepButton;

    @FXML
    private void runOneStepButtonClicked() {

    }

    public ObservableList<HeapEntry> getHeapEntries(IHeap heap) {
        ObservableList<HeapEntry> heapEntries = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Value> mapElement : heap.getContent().entrySet()) {
            heapEntries.add(new HeapEntry(mapElement.getKey(), mapElement.getValue()));
        }

//        // for testing purposes:
//            heapEntries.add(new HeapEntry(1, new StringValue("hello")));
//            heapEntries.add(new HeapEntry(2, new StringValue("da")));
//            heapEntries.add(new HeapEntry(3, new IntValue(123)));


        return heapEntries;
    }

    public void showData() {
        IRepository repo = activePrg.getRepository();
        List<PrgState> prgList = repo.getPrgList();
        IHeap heap = prgList.get(0).getHeap();
        IOutputList outList = prgList.get(0).getOutList();
        IFileTable fileTable = prgList.get(0).getFileTable();

        //prgIDsListView
        for(PrgState prg : prgList) {
            prgIDsListView.getItems().add(prg.getThreadID());
        }

        //nrOfPrgStatesTextField
        nrOfPrgStatesTextField.setText("Nr. of PrgStates: " + Integer.toString(prgList.size()));

        //heapTableView
        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        heapTableView.setItems(getHeapEntries(heap));

        //outputListView
        for(Value val: outList.getList()) {
            outputListView.getItems().add(val);
        }

        //fileTableListView
        for (StringValue val : fileTable.getContent().keySet()) {
           fileTableListView.getItems().add(val);
           System.out.println(val);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showData();
    }
}
