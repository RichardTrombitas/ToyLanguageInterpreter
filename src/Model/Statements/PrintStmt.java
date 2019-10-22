package Model.Statements;

import Model.PrgState;
import Model.Statements.IStmt;

public class PrintStmt implements IStmt {
    Exp exp;
    //...
    @Override
    public String toString(){ return "print(" +exp.toString()+")"; }

    public PrgState execute(PrgState state){
        //...
        return state;
    }
    //...
}

