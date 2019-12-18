package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Map;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; // 1 - and, 2 - or

    public LogicExp(Exp e1, Exp e2, int op) throws MyException {
        this.e1 = e1;
        this.e2 = e2;
        if(op > 2 || op < 1) throw new MyException("invalid operator");
        this.op = op;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder(e1.toString()).append(" ");
        switch(op){
            case 1:
                res.append("and");
            case 2:
                res.append("or");
        }
        res.append(" ").append(e2.toString());
        return res.toString();
    }

    @Override
    public Value eval(ISymTable tbl, IHeap hp) throws MyException {
        Value nr1 = e1.eval(tbl, hp);
        if(nr1.getType().equals(new BoolType())){
            Value nr2 = e2.eval(tbl, hp);
            if(nr2.getType().equals(new BoolType())){
                BoolValue b1 = (BoolValue) nr1;
                BoolValue b2 = (BoolValue) nr2;
                if(op == 1) return new BoolValue(b1.getVal() && b2.getVal());
                if(op == 2) return new BoolValue(b1.getVal() || b2.getVal());
            }
            else{
                throw new MyException("the second operand is not a boolean");
            }
        }
        else {
            throw new MyException("the first operand is not a boolean");
        }
        return new BoolValue(false);
    }

    public Type typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else {
                throw new MyException("The second operand (" + e2.toString() + ") is of type "
                        + typ2.toString() + " instead of bool!");
            }
        }   else {
            throw new MyException("The first operand (" + e1.toString() + ") is of type "
                    + typ1.toString() + " instead of bool!");
        }
    }
}
