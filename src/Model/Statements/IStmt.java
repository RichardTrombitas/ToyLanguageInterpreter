package Model.Statements;

import Model.MyException;
import Model.PrgState;
import Model.Types.Type;

import java.io.IOException;
import java.util.Map;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;

    Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException;
}
