package View.GUI;


import Controller.Controller;
import Model.Data.*;
import Model.Expressions.*;
import Model.MyException;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PrgSelectionCtrl implements Initializable {

    @FXML
    private ListView<String> prgListView;

    @FXML
    private Button selectButton;

    private List<Controller> ctrlList;


    public void display() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Program selection");
        Parent root = FXMLLoader.load(getClass().getResource("PrgSelection.fxml"));
        window.setScene(new Scene(root));
        window.showAndWait();
    }

    public void selectButtonClicked(ActionEvent event) {
        MainCtrl.activeExample = ctrlList.get(prgListView.getSelectionModel().getSelectedIndex());

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ctrlList = new ArrayList<>();

        //int v;
        //v = 2;
        //Print(v);
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        SymTable symtbl1 = new SymTable();
        OutputList out1 = new OutputList();
        ExeStack stk1 = new ExeStack();
        FileTable ft1 = new FileTable();
        Heap hp1 = new Heap();
        BarrierTable bt1 = new BarrierTable();
        PrgState prg1 = new PrgState(stk1, symtbl1, out1, ex1, ft1, hp1, bt1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);
        try {
            ex1.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr1);
        prgListView.getItems().add("ex1: " + ex1.toString());

        //int a;
        //int b;
        //a = 2 + 3 * 5;
        //b = a + 1;
        //Print(b);
        IStmt ex2 = null;
        try {
            ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                                    new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                    new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"),
                                            new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        SymTable symtbl2 = new SymTable();
        OutputList out2 = new OutputList();
        ExeStack stk2 = new ExeStack();
        FileTable ft2 = new FileTable();
        Heap hp2 = new Heap();
        BarrierTable bt2 = new BarrierTable();
        PrgState prg2 = new PrgState(stk2, symtbl2, out2, ex2, ft2, hp2, bt2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);
        assert ex2 != null;
        try {

            ex2.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr2);
        prgListView.getItems().add("ex2: " + ex2.toString());

        //bool a;
        //int v;
        //a = true;
        //(If a Then v = 2 Else v = 3);
        //Print(v);
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v",
                                        new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));

        SymTable symtbl3 = new SymTable();
        OutputList out3 = new OutputList();
        ExeStack stk3 = new ExeStack();
        FileTable ft3 = new FileTable();
        Heap hp3 = new Heap();
        BarrierTable bt3 = new BarrierTable();
        PrgState prg3 = new PrgState(stk3, symtbl3, out3, ex3, ft3, hp3, bt3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);
        try {
            ex3.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr3);
        prgListView.getItems().add("ex3: " + ex3.toString());

        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //print(v);
        //print(a);
        IStmt ex4 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        SymTable symtbl4 = new SymTable();
        OutputList out4 = new OutputList();
        ExeStack stk4 = new ExeStack();
        FileTable ft4 = new FileTable();
        Heap hp4 = new Heap();
        BarrierTable bt4 = new BarrierTable();
        PrgState prg4 = new PrgState(stk4, symtbl4, out4, ex4, ft4, hp4, bt4);
        IRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);
        try {
            ex4.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr4);
        prgListView.getItems().add("ex4: " + ex4.toString());


        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //print(rH(v));
        //print(rH(rH(a))+5);
        IStmt ex5 = null;
        try {
            ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                            new ValueExp(new IntValue(5)), 1)))))));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        SymTable symtbl5 = new SymTable();
        OutputList out5 = new OutputList();
        ExeStack stk5 = new ExeStack();
        FileTable ft5 = new FileTable();
        Heap hp5 = new Heap();
        BarrierTable bt5 = new BarrierTable();
        PrgState prg5 = new PrgState(stk5, symtbl5, out5, ex5, ft5, hp5, bt5);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);
        assert ex5 != null;
        try {
            ex5.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr5);
        prgListView.getItems().add("ex5: " + ex5.toString());


        //Ref int v;
        //new(v,20);
        //print(rH(v));
        //wH(v,30);
        //print(rH(v)+5);
        IStmt ex6 = null;
        try {
            ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")),
                                                    new ValueExp(new IntValue(5)), 1))))));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }


        SymTable symtbl6 = new SymTable();
        OutputList out6 = new OutputList();
        ExeStack stk6 = new ExeStack();
        FileTable ft6 = new FileTable();
        Heap hp6 = new Heap();
        BarrierTable bt6 = new BarrierTable();
        PrgState prg6 = new PrgState(stk6, symtbl6, out6, ex6, ft6, hp6, bt6);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);
        assert ex6 != null;
        try {
            ex6.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr6);
        prgListView.getItems().add("ex6: " + ex6.toString());


        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //new(v,30);
        //print(rH(rH(a)))
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

        SymTable symtbl7 = new SymTable();
        OutputList out7 = new OutputList();
        ExeStack stk7 = new ExeStack();
        FileTable ft7 = new FileTable();
        Heap hp7 = new Heap();
        BarrierTable bt7 = new BarrierTable();
        PrgState prg7 = new PrgState(stk7, symtbl7, out7, ex7, ft7, hp7, bt7);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);
        try {
            ex7.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr7);
        prgListView.getItems().add("ex7: " + ex7.toString());


        //int v;
        //v=4;
        //(while (v>0) print(v);v=v-1);
        //print(v);
        IStmt ex8 = null;
        try {
            ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                            new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                            new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),
                                    new PrintStmt(new VarExp("v")))));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        SymTable symtbl8 = new SymTable();
        OutputList out8 = new OutputList();
        ExeStack stk8 = new ExeStack();
        FileTable ft8 = new FileTable();
        Heap hp8 = new Heap();
        BarrierTable bt8 = new BarrierTable();
        PrgState prg8 = new PrgState(stk8, symtbl8, out8, ex8, ft8, hp8, bt8);
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);
        assert ex8 != null;
        try {
            ex8.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr8);
        prgListView.getItems().add("ex8: " + ex8.toString());


        //int v;
        //Ref int a;
        //v=10;
        //new(a,22);
        //fork(wH(a,30);v=32;print(v);print(rH(a)));
        //print(v);
        //print(rH(a));
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                        new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

        SymTable symtbl9 = new SymTable();
        OutputList out9 = new OutputList();
        ExeStack stk9 = new ExeStack();
        FileTable ft9 = new FileTable();
        Heap hp9 = new Heap();
        BarrierTable bt9 = new BarrierTable();
        PrgState prg9 = new PrgState(stk9, symtbl9, out9, ex9, ft9, hp9, bt9);
        IRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);
        try {
            ex9.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr9);
        prgListView.getItems().add("ex9: " + ex9.toString());

        //int a; int b; int c
        //a=1; b=2; c=5;
        //switch(a*10)
        //(case (b*c) print(a);print(b))
        //(case (10) print(100);print(200))
        //(default print(300));
        //print(300)
        IStmt ex10 = null;
        try {
            ex10 = new CompStmt(new VarDeclStmt("a", new IntType()),
                    new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new VarDeclStmt("c", new IntType()),
                                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                    new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                            new CompStmt(new SwitchStmt(new ArithExp(new VarExp("a"), new ValueExp(new IntValue(10)),
                                                                    3), new ArithExp(new VarExp("b"), new VarExp("c"), 3),
                                                                    new ValueExp(new IntValue(10)),
                                                                    new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                    new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                                            new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                    new PrintStmt(new ValueExp(new IntValue(300)))),
                                                                    new PrintStmt(new ValueExp(new IntValue(300))))))))));
        } catch (MyException e) {
            e.printStackTrace();
        }

        SymTable symtbl10 = new SymTable();
        OutputList out10 = new OutputList();
        ExeStack stk10 = new ExeStack();
        FileTable ft10 = new FileTable();
        Heap hp10 = new Heap();
        BarrierTable bt10 = new BarrierTable();
        PrgState prg10 = new PrgState(stk10, symtbl10, out10, ex10, ft10, hp10, bt10);
        IRepository repo10 = new Repository(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);
        try {
            assert ex10 != null;
            ex10.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr10);
        prgListView.getItems().add("ex10: " + ex10.toString());


//        int v;
//        int x;
//        int y;
//        v=0;
//        (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
//        x=1;
//        nop;
//        y=3;
//        nop;
//        print(v*10)
//        The final Out should be {0,1,2,30}
        IStmt ex11 = null;
        try {
            ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new VarDeclStmt("x", new IntType()),
                            new CompStmt(new VarDeclStmt("y", new IntType()),
                                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                                            new CompStmt(
                                                    new RepeatUntilStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                                            new AssignStmt("v", new ArithExp(new VarExp("v"),
                                                                    new ValueExp(new IntValue(1)), 2)))),
                                                            new AssignStmt("v", new ArithExp(new VarExp("v"),
                                                                    new ValueExp(new IntValue(1)), 1))),
                                                            new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==")),
                                                    new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                            new CompStmt(new NopStmt(),
                                                                    new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))),
                                                                            new CompStmt(new NopStmt(),
                                                                                    new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), 3)))))))))));
        } catch (MyException e) {
            e.printStackTrace();
        }

        SymTable symtbl11 = new SymTable();
        OutputList out11 = new OutputList();
        ExeStack stk11 = new ExeStack();
        FileTable ft11 = new FileTable();
        Heap hp11 = new Heap();
        BarrierTable bt11 = new BarrierTable();
        PrgState prg11 = new PrgState(stk11, symtbl11, out11, ex11, ft11, hp11, bt11);
        IRepository repo11 = new Repository(prg11, "log11.txt");
        Controller ctr11 = new Controller(repo11);
        try {
            assert ex11 != null;
            ex11.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr11);
        prgListView.getItems().add("ex11: " + ex11.toString());


        //Ref int v1;
        //Ref int v2;
        //Ref int v3;
        //int cnt;
        //new(v1,2);
        //new(v2,3);
        //new(v3,4);
        //newBarrier(cnt,rH(v2));
        //fork(await(cnt);wh(v1,rh(v1)*10));
        //print(rh(v1)));
        //fork(await(cnt);wh(v2,rh(v2)*10));
        //wh(v2,rh(v2)*10));
        //print(rh(v2)));
        //await(cnt);
        //print(rH(v3))
        //The final Out should be {4,20,300}
        IStmt ex12 = null;
        try {
            ex12 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                    new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                            new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                    new CompStmt(new VarDeclStmt("cnt", new IntType()),
                                            new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(2))),
                                                    new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(3))),
                                                            new CompStmt(new NewStmt("v3", new ValueExp(new IntValue(4))),
                                                                    new CompStmt(new NewBarrierStmt("cnt", new ReadHeapExp(new VarExp("v2"))),
                                                                            new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                    new CompStmt(new WriteHeapStmt("v1", new ArithExp(new ReadHeapExp(
                                                                                            new VarExp("v1")),
                                                                                            new ValueExp(new IntValue(10)), 3)),
                                                                                            new PrintStmt(new ReadHeapExp(new VarExp("v1")))))),
                                                                                    new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                            new CompStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(
                                                                                                    new VarExp("v2")),
                                                                                                    new ValueExp(new IntValue(10)), 3)),
                                                                                                    new CompStmt(new WriteHeapStmt("v2",
                                                                                                            new ArithExp(new ReadHeapExp(new VarExp("v2")
                                                                                                            ), new ValueExp(new IntValue(10)), 3)),
                                                                                                            new PrintStmt(new ReadHeapExp(new VarExp("v2"))))))),
                                                                                            new CompStmt(new AwaitStmt("cnt"),
                                                                                                    new PrintStmt(new ReadHeapExp(new VarExp("v3"))))))))))))));

        } catch (MyException e) {
            e.printStackTrace();
        }

        SymTable symtbl12 = new SymTable();
        OutputList out12 = new OutputList();
        ExeStack stk12 = new ExeStack();
        FileTable ft12 = new FileTable();
        Heap hp12 = new Heap();
        BarrierTable bt12 = new BarrierTable();
        PrgState prg12 = new PrgState(stk12, symtbl12, out12, ex12, ft12, hp12, bt12);
        IRepository repo12 = new Repository(prg12, "log12.txt");
        Controller ctr12 = new Controller(repo12);
        try {
            assert ex12 != null;
            ex12.typecheck(new HashMap<>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        ctrlList.add(ctr12);
        prgListView.getItems().add("ex12: " + ex12.toString());

    }
}


