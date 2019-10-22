package Model.Expressions;
import Model.Values.Value;

public class VarExp implements Exp {
    String id;
    //....

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) {
        return tbl.lookup(id);
    }
    //....
}
