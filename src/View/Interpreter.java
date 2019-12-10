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

public class Interpreter {

    public static void main(String[] args) throws MyException{

        //hard coded programs here

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
        Controller ctr1 = new Controller(repo1);

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
        Controller ctr2 = new Controller(repo2);

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
        Controller ctr3 = new Controller(repo3);

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
        Controller ctr4 = new Controller(repo4);

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
        Controller ctr5 = new Controller(repo5);

        //Ref int v;
        //new(v,20);
        //print(rH(v));
        //wH(v,30);
        //print(rH(v)+5);
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                    new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")),
                    new ValueExp(new IntValue(5)), 1))))));

        SymTable symtbl6 = new SymTable();
        OutputList out6 = new OutputList();
        ExeStack stk6 = new ExeStack();
        FileTable ft6 = new FileTable();
        Heap hp6 = new Heap();
        PrgState prg6 = new PrgState(stk6, symtbl6, out6, ex6, ft6, hp6);
        IRepository repo6 = new Repository(prg6,"log6.txt");
        Controller ctr6 = new Controller(repo6);

        //Ref int v;
        //new(v,20);
        //Ref Ref int a;
        //new(a,v);
        //new(v,30);
        //print(rH(rH(a)))
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                    new CompStmt(new HeapAllocStmt("a", new VarExp("v")),
                    new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))),
                    new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

        SymTable symtbl7 = new SymTable();
        OutputList out7 = new OutputList();
        ExeStack stk7 = new ExeStack();
        FileTable ft7 = new FileTable();
        Heap hp7 = new Heap();
        PrgState prg7 = new PrgState(stk7, symtbl7, out7, ex7, ft7, hp7);
        IRepository repo7 = new Repository(prg7,"log7.txt");
        Controller ctr7 = new Controller(repo7);

        //int v;
        //v=4;
        //(while (v>0) print(v);v=v-1);
        //print(v);
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                    new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)),">"),
                    new CompStmt(new PrintStmt(new VarExp("v")),
                    new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),
                    new PrintStmt(new VarExp("v")))));

        SymTable symtbl8 = new SymTable();
        OutputList out8 = new OutputList();
        ExeStack stk8 = new ExeStack();
        FileTable ft8 = new FileTable();
        Heap hp8 = new Heap();
        PrgState prg8 = new PrgState(stk8, symtbl8, out8, ex8, ft8, hp8);
        IRepository repo8 = new Repository(prg8,"log8.txt");
        Controller ctr8 = new Controller(repo8);

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
                    new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))),
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
        PrgState prg9 = new PrgState(stk9, symtbl9, out9, ex9, ft9, hp9);
        IRepository repo9 = new Repository(prg9,"log9.txt");
        Controller ctr9 = new Controller(repo9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.show();
    }
}
