package Model.Statements;

import Model.PrgState;

public interface IStmt {
    //...
    PrgState execute(PrgState state);
    //...

}
