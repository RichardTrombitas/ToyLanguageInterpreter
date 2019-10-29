package Model.Expressions;
import Model.DataStructures.MyIDictionary;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws Exception;

}
