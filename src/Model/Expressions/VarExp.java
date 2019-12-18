package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class VarExp implements Exp {
    private String id;

    public VarExp(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public Value eval(ISymTable tbl, IHeap hp) {
        return tbl.lookup(id);
    }

    public Type typecheck(Map<String, Type> typeEnv) throws MyException {
        if (!typeEnv.containsKey(id)) {
            throw new MyException("The variable " + id + " is not defined!");
        }
        return typeEnv.get(id);
    }
}
