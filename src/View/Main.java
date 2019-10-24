package View;

import Model.*;
import Model.DataStructures.*;
import Model.Statements.IStmt;
import Model.Values.Value;

public class Main {

    public static void main(String[] args) {
        // write your code here
        MyIStack<IStmt> stk = new MyStack<IStmt>(....);
        MyIDictionary<String, Value> symtbl = new MyDictionary<String, Value>(...);
        MyIList<Value> out = new MyList<Value>(...);
        PrgState crtPrgState = new PrgState(stk, symtbl, out);

        // hard coded programs here (examples on site)
    }
}
