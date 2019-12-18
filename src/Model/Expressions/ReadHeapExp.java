package Model.Expressions;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.util.Map;

public class ReadHeapExp implements Exp {
    private Exp exp;

    public ReadHeapExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }

    public Value eval(ISymTable tbl, IHeap hp) throws MyException {
        Value val = exp.eval(tbl, hp);
        if (!(val.getType() instanceof RefType)) {
            throw new MyException(val.toString() + "is not of RefType!");
        }

        int addr = ((RefValue) val).getAddr();

        if (!hp.isDefined(addr)) {
            throw new MyException("There is no value at address " + addr + " on the heap!");
        }

        return hp.lookup(addr);
    }

    public Type typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typ = exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("The RH argument (" + exp.toString() + ") is of type " +
                    typ.toString() + " instead of RefType!");
    }

}
