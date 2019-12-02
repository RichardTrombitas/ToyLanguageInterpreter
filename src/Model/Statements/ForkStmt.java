package Model.Statements;

import Model.Data.*;
import Model.PrgState;

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
        es.push(stmt);
        ISymTable symTbl = state.getSymTbl().deepCopy();
        IHeap hp = state.getHeap();
        IFileTable ft = state.getFileTable();
        IOutputList out = state.getOutList();

        return new PrgState(es, symTbl, out, new NopStmt(), ft, hp);
    }
}
