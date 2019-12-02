package Model.Statements;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.*;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapAllocStmt implements IStmt {
    private String varName;
    private Exp exp;

    public HeapAllocStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + varName + "," + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTbl();
        IHeap hp = state.getHeap();

        if (!symTbl.isDefined(varName)) {
            throw new MyException(varName + " is not a variable in the SymTable!");
        }
        Type varType = symTbl.lookup(varName).getType();
        if (!(varType instanceof RefType)) {
            throw new MyException("The type of " + varName + " is not RefType!");
        }

        Value value = exp.eval(symTbl, hp);
        Type valueType = value.getType();
        Type locationType = ((RefType) varType).getInner();

        if (!valueType.equals(locationType)) {
            throw new MyException(valueType.toString() + " is not equal to " + locationType.toString() + "!");
        }

        IHeap heap = state.getHeap();
        int addr = heap.allocate(value);

        symTbl.update(varName, new RefValue(addr, locationType));

        return null;
    }
}
