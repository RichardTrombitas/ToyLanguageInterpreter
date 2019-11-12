package Model.Statements;
import Model.MyException;
import Model.PrgState;

public interface IStmt {
    void execute(PrgState state) throws MyException;
}
