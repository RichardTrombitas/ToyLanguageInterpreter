package Model.Statements;
import Model.MyException;
import Model.PrgState;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;
}
