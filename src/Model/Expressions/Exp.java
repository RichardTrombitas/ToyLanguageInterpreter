package Model.Expressions;
import Model.CollectionInstances.ISymTable;
import Model.MyException;
import Model.Values.Value;

public interface Exp {
    Value eval(ISymTable tbl) throws MyException;
}
