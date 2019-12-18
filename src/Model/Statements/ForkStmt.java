package Model.Statements;

import Model.Data.*;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;

import java.util.Map;

public class ForkStmt implements IStmt {
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    public PrgState execute(PrgState state) {
        IExeStack es = new ExeStack();
        ISymTable symTbl = state.getSymTbl().deepCopy();
        IHeap hp = state.getHeap();
        IFileTable ft = state.getFileTable();
        IOutputList out = state.getOutList();

        return new PrgState(es, symTbl, out, stmt, ft, hp);
    }

    public Map<String, Type> typecheck(Map<String,Type> typeEnv) throws MyException {
        return stmt.typecheck(typeEnv);
    }
}
