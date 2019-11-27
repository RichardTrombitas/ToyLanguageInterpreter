package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Values.Value;

public interface Exp {
    Value eval(ISymTable tbl, IHeap hp) throws MyException;
}
