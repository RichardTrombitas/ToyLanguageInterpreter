package Model.Statements;

import Model.Data.IExeStack;
import Model.PrgState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first, IStmt snd){
        this.first = first;
        this.snd = snd;
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }

    public PrgState execute(PrgState state){
        IExeStack stk = state.getStk();
        stk.push(snd);
        stk.push(first);

        return null;
    }
}
