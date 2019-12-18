package Model.Statements;

import Model.Data.IExeStack;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;

import java.util.Map;

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

    public Map<String, Type> typecheck(Map<String,Type> typeEnv) throws MyException {
        return snd.typecheck(first.typecheck(typeEnv));
    }
}
