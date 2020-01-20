package Model.Expressions;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Map;

public class NotExp implements Exp {
    private Exp exp;

    public NotExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "not(" + exp.toString() + ")";
    }

    @Override
    public Value eval(ISymTable tbl, IHeap hp) throws MyException {
        Value res = exp.eval(tbl, hp);
        if (!res.getType().equals(new BoolType())) {
            throw new MyException("The expression (" + exp.toString() + ") doesn't evaluate to a BoolType!");
        } else {
            return new BoolValue(!((BoolValue) res).getVal());
        }
    }

    @Override
    public Type typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typExp = exp.typecheck(typeEnv);

        if (typExp.equals(new BoolType())) {
                return new BoolType();
        } else {
            throw new MyException("The expression (" + exp.toString() + ") doesn't evaluate to a BoolType!");
        }
    }
}
