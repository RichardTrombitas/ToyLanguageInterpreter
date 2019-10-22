package Model.Expressions;

import Model.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide
    //....

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws Exception {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = v1.getVal();
                n2 = v2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) {
                    if (n2 == 0) {
                        throw new Exception("division by zero");
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
    }
}