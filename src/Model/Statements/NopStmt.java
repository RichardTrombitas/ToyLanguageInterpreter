package Model.Statements;

import Model.DataStructures.MyIStack;
import Model.MyException;
import Model.PrgState;

public class NopStmt implements IStmt{

    @Override
    public String toString(){ return "no operation"; }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        return state;
    }
}
