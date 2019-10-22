package Model.Expressions;
import Model.Values.Value;
public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op;
    //....

    @Override
    public Value eval(MyIDictionary<String, Integer> tbl) {
        //....
    }
    //....
}
