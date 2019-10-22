package Model.Statements;

import Model.PrgState;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;
    // ....

    IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) {
        // ......
        return state;
    }
    // ...
}


