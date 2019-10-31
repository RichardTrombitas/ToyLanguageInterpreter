package Model.Expressions;
import Model.DataStructures.MyIDictionary;
import Model.Values.Value;
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
    public Value eval(MyIDictionary<String, Value> tbl){
        return e;
    }

}
