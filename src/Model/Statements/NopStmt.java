package Model.Statements;
import Model.PrgState;

public class NopStmt implements IStmt{

    @Override
    public String toString(){ return "no operation"; }

    public void execute(PrgState state) {
    }
}
