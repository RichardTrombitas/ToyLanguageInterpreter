package Model;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.Statements.IStmt;
import Model.Values.Value;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = deepCopy(prg);    //recreate the entire original prg
        stk.push(prg);
    }
    //.....
}