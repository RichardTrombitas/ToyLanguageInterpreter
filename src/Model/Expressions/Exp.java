package Model.Expressions;
import Model.Collections.MyIDictionary;
import Model.MyException;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyException;
}
