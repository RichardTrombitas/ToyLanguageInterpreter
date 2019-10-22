package Model.Expressions;
import Model.Values.Value;
public class ValueExp implements Exp {
    Value e;
    //....

    @Override
    public Value eval(MyIDictionary<String, Integer> tbl) {
        return e;
    }
    //....
}
