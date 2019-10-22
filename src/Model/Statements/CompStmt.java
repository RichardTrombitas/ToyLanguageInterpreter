package Model.Statements;

import Model.PrgState;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;
    //...
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
    public PrgState execute(PrgState state){
        MyIStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        return state;
    }
}
