package Model.Statements;
import Model.CollectionInstances.IOutputList;
import Model.CollectionInstances.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    @Override
    public String toString(){ return "print(" +exp.toString()+")"; }

    public void execute(PrgState state) throws MyException {

        IOutputList out = state.getOutList();
        ISymTable symTable = state.getSymTbl();

        out.add(exp.eval(symTable));
    }
  
}

