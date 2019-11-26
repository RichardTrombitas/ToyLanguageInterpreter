package View;

import Controller.Controller;
import Model.*;
import Model.CollectionInstances.*;
import Model.Expressions.ArithExp;
import Model.Expressions.ReadHeapExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.binding.StringExpression;

import java.io.IOException;

public class Interpreter {

    public static void main(String[] args) throws MyException, IOException {

        // hard coded programs here

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
        PrgState prg1 = new PrgState(stk1, symtbl1, out1, ex1, ft1, hp1);
        IRepository repo1 = new Repository(prg1,"log1.txt");
        Controller ctr1 = new Controller(repo1, true);

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
        SymTable symtbl2 = new SymTable();
        OutputList out2 = new OutputList();
        ExeStack stk2 = new ExeStack();
        FileTable ft2 = new FileTable();
        Heap hp2= new Heap();
        PrgState prg2 = new PrgState(stk2, symtbl2, out2, ex2, ft2, hp2);
        IRepository repo2 = new Repository(prg2,"log2.txt");
        Controller ctr2 = new Controller(repo2, true);

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
        PrgState prg3 = new PrgState(stk3, symtbl3, out3, ex3, ft3, hp3);
        IRepository repo3 = new Repository(prg3,"log3.txt");
        Controller ctr3 = new Controller(repo3, true);

        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //print(v);
        //print(a);
        IStmt ex4 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        SymTable symtbl4 = new SymTable();
        OutputList out4 = new OutputList();
        ExeStack stk4 = new ExeStack();
        FileTable ft4 = new FileTable();
        Heap hp4 = new Heap();
        PrgState prg4 = new PrgState(stk4, symtbl4, out4, ex4, ft4, hp4);
        IRepository repo4 = new Repository(prg4,"log4.txt");
        Controller ctr4 = new Controller(repo4, true);

        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //print(rH(v));
        //print(rH(rH(a))+5);
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                    new ValueExp(new IntValue(5)), 1)))))));
        SymTable symtbl5 = new SymTable();
        OutputList out5 = new OutputList();
        ExeStack stk5 = new ExeStack();
        FileTable ft5 = new FileTable();
        Heap hp5 = new Heap();
        PrgState prg5 = new PrgState(stk5, symtbl5, out5, ex5, ft5, hp5);
        IRepository repo5 = new Repository(prg5,"log5.txt");
        Controller ctr5 = new Controller(repo5, true);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.show();

//        ctr1.allStep();
//
//        ValueExp varf = new ValueExp(new StringValue("test.in"));
//        OpenRFileStmt s = new OpenRFileStmt(varf);
//        s.execute(prg1);
//
//        ReadFileStmt s1 = new ReadFileStmt(varf,  "v");
//        s1.execute(prg1);
//
//        System.out.println("v="+prg1.getSymTbl().lookup("v"));
//
//
//        ReadFileStmt s2 = new ReadFileStmt(varf,  "v");
//        s2.execute(prg1);
//
//        System.out.println("v="+prg1.getSymTbl().lookup("v"));
//
//        CloseRFileStmt c = new CloseRFileStmt(varf);


    }
}
