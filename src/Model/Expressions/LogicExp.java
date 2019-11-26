package Model.Expressions;
import Model.CollectionInstances.ISymTable;
import Model.MyException;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

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
    public Value eval(ISymTable tbl) throws MyException {
        Value nr1 = e1.eval(tbl);
        if(nr1.getType().equals(new BoolType())){
            Value nr2 = e2.eval(tbl);
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
}
