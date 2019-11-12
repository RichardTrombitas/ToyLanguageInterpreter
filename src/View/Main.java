package View;

import Controller.Controller;
import Model.*;
import Model.DataStructures.*;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import Repository.Repository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws MyException, IOException {

        // hard coded programs here

        //int v;
        //v = 2;
        //Print(v);
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> stk1 = new MyStack<>();
        stk1.push(ex1);

        //int a;
        //int b;
        //a = 2 + 3 * 5;
        //b = a + 1;
        //Print(b);
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),
                                new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)),
                                    new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"),
                                        new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        MyIStack<IStmt> stk2 = new MyStack<>();
        stk2.push(ex2);

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
        MyIStack<IStmt> stk3 = new MyStack<>();
        stk3.push(ex3);


        MyIDictionary<String, Value> symtbl = new MyDictionary<>();
        MyIList<Value> out = new MyList<>();
        MyIStack<IStmt> stk = new MyStack<>();
        PrgState crtPrgState = new PrgState(stk, symtbl, out, new NopStmt());
        Repository repo = new Repository(crtPrgState);
        Controller ctrl = new Controller(repo, true);

        int menuOption = UI.askMenuOption();
        while(menuOption!=0){
            switch(menuOption){
                case 1:
                    int whichProgram = UI.askWhichProgram();
                    switch(whichProgram){
                        case 1:
                            crtPrgState.setStk(stk1);
                            break;
                        case 2:
                            crtPrgState.setStk(stk2);
                            break;
                        case 3:
                            crtPrgState.setStk(stk3);
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 2:
                    ctrl.allStep();
                    return;
            }
            menuOption = UI.askMenuOption();
        }
    }
}
