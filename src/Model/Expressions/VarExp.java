package Model.Expressions;
import Model.Collections.MyIDictionary;
import Model.Values.Value;

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
    public Value eval(MyIDictionary<String, Value> tbl) {
        return tbl.lookup(id);
    }
}
