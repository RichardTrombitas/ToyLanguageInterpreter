package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.MyException;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public interface Exp {
    Value eval(ISymTable tbl, IHeap hp) throws MyException;
    Type typecheck(Map<String, Type> typeEnv) throws MyException;
}
