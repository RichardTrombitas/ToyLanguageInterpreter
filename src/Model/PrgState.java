package Model;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    //private IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> ft) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        //originalProgram = deepCopy(prg);    //recreate the entire original prg
        stk.push(prg);
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }
    public void setStk(MyIStack<IStmt> stk) {
        exeStack = stk;
    }

    public MyIDictionary<String, Value> getTbl() {return symTable;}
    public void setTbl(MyIDictionary<String, Value> symtbl) {
        symTable = symtbl;
    }

    public MyIList<Value> getOutList() {return out;}
    public void setOutList(MyIList<Value> ot) {
        out = ot;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {return fileTable;}
    public void setFileTable(MyIDictionary<StringValue, BufferedReader> ft) {
        fileTable = ft;
    }

    @Override
    public String toString(){
        return exeStack.toString() + "\n" + symTable.toString() + "\n" + out.toString() + "\n";
    }
}
