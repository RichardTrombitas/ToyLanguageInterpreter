package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.MyException;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExp implements Exp {
    private Exp e1, e2;
    private String rel;

    public RelationalExp(Exp e1, Exp e2, String rel) throws MyException {
        if(!rel.equals("<") && !rel.equals("<=") && !rel.equals("==") && !rel.equals("!=") && !rel.equals(">") && !rel.equals(">=")){
            throw new MyException(rel+"is an invalid relation!");
        }
        this.e1 = e1;
        this.e2 = e2;
        this.rel = rel;
    }

    @Override
    public BoolValue eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value nr1 = e1.eval(tbl);
        if(nr1.getType().equals(new IntType())){
            Value nr2 = e2.eval(tbl);
            if(nr2.getType().equals(new IntType())){
                IntValue b1 = (IntValue) nr1;
                IntValue b2 = (IntValue) nr2;
                if(rel.equals("<")) return new BoolValue(b1.getVal() < b2.getVal());
                if(rel.equals("<=")) return new BoolValue(b1.getVal() <= b2.getVal());
                if(rel.equals("==")) return new BoolValue(b1.getVal() == b2.getVal());
                if(rel.equals("!=")) return new BoolValue(b1.getVal() != b2.getVal());
                if(rel.equals(">")) return new BoolValue(b1.getVal() > b2.getVal());
                if(rel.equals(">=")) return new BoolValue(b1.getVal() >= b2.getVal());
            }
            else{
                throw new MyException("The second operand is not an integer!");
            }
        }
        else {
            throw new MyException("The first operand is not an integer!");
        }
        return new BoolValue(false);
    }
}
