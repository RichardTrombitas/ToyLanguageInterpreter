package Model.Expressions;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

import java.util.Map;

public class RelationalExp implements Exp {
    private Exp e1, e2;
    private String rel;

    public RelationalExp(Exp e1, Exp e2, String rel) throws MyException {
        if (!rel.equals("<") && !rel.equals("<=") && !rel.equals("==") && !rel.equals("!=") && !rel.equals(">") && !rel.equals(">=")) {
            throw new MyException(rel + "is an invalid relation!");
        }
        this.e1 = e1;
        this.e2 = e2;
        this.rel = rel;
    }

    @Override
    public String toString() {
        return e1.toString() + rel + e2.toString();
    }

    @Override
    public BoolValue eval(ISymTable tbl, IHeap hp) throws MyException {
        Value nr1 = e1.eval(tbl, hp);
        if (nr1.getType().equals(new IntType())) {
            Value nr2 = e2.eval(tbl, hp);
            if (nr2.getType().equals(new IntType())) {
                IntValue b1 = (IntValue) nr1;
                IntValue b2 = (IntValue) nr2;
                if (rel.equals("<")) return new BoolValue(b1.getVal() < b2.getVal());
                if (rel.equals("<=")) return new BoolValue(b1.getVal() <= b2.getVal());
                if (rel.equals("==")) return new BoolValue(b1.getVal() == b2.getVal());
                if (rel.equals("!=")) return new BoolValue(b1.getVal() != b2.getVal());
                if (rel.equals(">")) return new BoolValue(b1.getVal() > b2.getVal());
                if (rel.equals(">=")) return new BoolValue(b1.getVal() >= b2.getVal());
            } else {
                throw new MyException("The second operand is not an integer!");
            }
        } else {
            throw new MyException("The first operand is not an integer!");
        }
        return new BoolValue(false);
    }

    public Type typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else {
                throw new MyException("The second operand (" + e2.toString() + ") is of type "
                        + typ2.toString() + " instead of int!");
            }
        } else {
            throw new MyException("The first operand (" + e1.toString() + ") is of type "
                    + typ1.toString() + " instead of int!");
        }
    }
}
