package Model.Statements;

import Model.Data.IHeap;
import Model.Data.IOutputList;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;

import java.util.Map;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {

        IOutputList out = state.getOutList();
        ISymTable symTable = state.getSymTbl();
        IHeap hp = state.getHeap();

        out.add(exp.eval(symTable, hp));

        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

}

