package View.GUI;

import Controller.Controller;
import Model.Data.*;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.IntValue;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MainCtrl implements Initializable {

    public static Controller activeExample;
    private static int selectedThread = 0;
    private ExecutorService executor;
    private List<PrgState> prgList;

    @FXML
    private Label symTblLabel;
    @FXML
    private Label exeStackLabel;
    @FXML
    private ListView<Integer> prgIDsListView;
    @FXML
    private TextField nrOfPrgStatesTextField;
    @FXML
    private TableView<SymTblEntry> symTableView;
    @FXML
    private TableColumn<HeapEntry, Integer> symTblNameColumn;
    @FXML
    private TableColumn<HeapEntry, Integer> symTblValueColumn;
    @FXML
    private ListView<IStmt> exeStackListView;
    @FXML
    private ListView<StringValue> fileTableListView;
    @FXML
    private ListView<Value> outputListView;
    @FXML
    private TableView<HeapEntry> heapTableView;
    @FXML
    private TableColumn<HeapEntry, Integer> heapAddressColumn;
    @FXML
    private TableColumn<HeapEntry, Value> heapValueColumn;
    @FXML
    private Button runOneStepButton;

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

    public ObservableList<SymTblEntry> getSymTblEntries(ISymTable symTbl) {
        ObservableList<SymTblEntry> symTblEntries = FXCollections.observableArrayList();
        for (Map.Entry<String, Value> mapElement : symTbl.getContent().entrySet()) {
            symTblEntries.add(new SymTblEntry(mapElement.getKey(), mapElement.getValue()));
        }

//        // for testing purposes:
//            symTblEntries.add(new SymTblEntry("1", new StringValue("hello")));
//            symTblEntries.add(new SymTblEntry("2", new StringValue("da")));
//            symTblEntries.add(new SymTblEntry("3", new IntValue(123)));

        return symTblEntries;
    }

    private void showLocalData() {
        exeStackListView.getItems().clear();
        symTableView.getItems().clear();

        PrgState prg = prgList.get(selectedThread - prgList.get(0).getThreadID());

        //exeStackListView
        Stack<IStmt> stack = prg.getStk().getContent();
        ListIterator<IStmt> li = stack.listIterator(stack.size());
        while (li.hasPrevious()) {
            exeStackListView.getItems().add((IStmt) li.previous());
        }

        //symTableView
        ISymTable symTbl = prg.getSymTbl();
        symTblNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        symTblValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        symTableView.setItems(getSymTblEntries(symTbl));
    }

    public void showData() {
        prgIDsListView.getItems().clear();
        heapTableView.getItems().clear();
        outputListView.getItems().clear();
        fileTableListView.getItems().clear();

        IHeap heap = prgList.get(0).getHeap();
        IOutputList outList = prgList.get(0).getOutList();
        IFileTable fileTable = prgList.get(0).getFileTable();

        //prgIDsListView
        for (PrgState prg : prgList) {
            prgIDsListView.getItems().add(prg.getThreadID());
        }

        //nrOfPrgStatesTextField
        nrOfPrgStatesTextField.setText("Nr. of PrgStates: " + prgList.size());

        //heapTableView
        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        heapTableView.setItems(getHeapEntries(heap));

        //outputListView
        for (Value val : outList.getList()) {
            outputListView.getItems().add(val);
        }

        //fileTableListView
        for (StringValue val : fileTable.getContent().keySet()) {
            fileTableListView.getItems().add(val);
            System.out.println(val);
        }

        //exeStackListView and symTableView
        if (selectedThread != 0) {
            showLocalData();
        }

    }

    @FXML
    private void runOneStepButtonClicked() throws InterruptedException {
        prgList = activeExample.removeCompletedPrg(activeExample.getRepository().getPrgList());

        if (prgList.size() > 0) {
            IHeap hp = prgList.get(0).getHeap();

            List<Integer> addressList = new ArrayList<>();
            prgList.forEach(prg -> addressList.addAll(activeExample.getAddrFromSymTable(prg.getSymTbl().getContent().values())));
            hp.setContent(activeExample.garbageCollector(addressList, hp));

            activeExample.oneStepForAllPrg(prgList, executor);

            showData();

            prgList = activeExample.removeCompletedPrg(activeExample.getRepository().getPrgList());
        }
        else {
            executor.shutdownNow();
            activeExample.getRepository().setPrgList(prgList);
            prgIDsListView.getItems().clear();
            nrOfPrgStatesTextField.setText("Nr. of PrgStates: 0");
            runOneStepButton.setDisable(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        executor = Executors.newFixedThreadPool(2);
        prgList = activeExample.getRepository().getPrgList();
        prgIDsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(prgIDsListView.getSelectionModel().getSelectedItem() != null) {
                MainCtrl.selectedThread = prgIDsListView.getSelectionModel().getSelectedItem();
                symTblLabel.setText("SymTbl (" + selectedThread + ")");
                exeStackLabel.setText("ExeStack (" + selectedThread + ")");
            }
            showLocalData();
        });
        showData();
    }
}
