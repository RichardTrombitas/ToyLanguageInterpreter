package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e){
        this.e = e;
    }

    @Override
    public String toString(){
        return e.toString();
    }

    @Override
    public Value eval(ISymTable symTable, IHeap hp){
        return e;
    }

    public Type typecheck(Map<String, Type> typeEnv) {
        return e.getType();
    }

}
