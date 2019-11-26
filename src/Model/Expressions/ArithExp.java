package Model.Expressions;

import Model.Collections.MyIDictionary;
import Model.MyException;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp e1, Exp e2, int op) throws MyException {
        this.e1 = e1;
        this.e2 = e2;
        if (op < 1 || op > 4) throw new MyException("invalid operator");
        this.op = op;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(e1.toString());
        switch (op) {
            case 1:
                res.append("+");
                break;
            case 2:
                res.append("-");
                break;
            case 3:
                res.append("*");
                break;
            case 4:
                res.append("/");
                break;
        }
        res.append(e2.toString());
        return res.toString();
    }


    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) {
                    if (n2 == 0) {
                        throw new MyException("division by zero");
                    } else {
                        return new IntValue(n1 / n2);
                    }
                } else {
                    throw new MyException("second operand is not an integer");
                }
            } else {
                throw new MyException("first operand is not an integer");
            }
        }
        return new IntValue(0);
    }
}