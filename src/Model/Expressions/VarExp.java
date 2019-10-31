package Model.Expressions;
import Model.DataStructures.MyIDictionary;
import Model.Values.Value;

public class VarExp implements Exp {
    String id;

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
    //....
}
