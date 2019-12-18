package Model.Statements;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;

import java.util.HashMap;
import java.util.Map;

public class NopStmt implements IStmt{

    @Override
    public String toString(){ return "no operation"; }

    public PrgState execute(PrgState state) {
        return null;
    }

    public Map<String, Type> typecheck(Map<String,Type> typeEnv) {
        return typeEnv;
    }
}
