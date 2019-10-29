package Model.Statements;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.Value;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    @Override
    public String toString(){ return "print(" +exp.toString()+")"; }

    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stk = state.getStk();
        stk.pop();

        MyIList<Value> out = state.getOutList();
        MyIDictionary<String, Value> symTable = state.getTbl();

        out.add(exp.eval(symTable));
        return state;
    }
  
}

