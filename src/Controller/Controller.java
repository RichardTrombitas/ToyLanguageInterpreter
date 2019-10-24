package Controller;

import Exceptions.StmtExecException;
import Model.DataStructures.MyIStack;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepository;

public class Controller {
    private IRepository repo;

    PrgState oneStep(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        if (stk.isEmpty()) throw new StmtExecException(...);
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    void allStep() {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type IRepository
        while (!prg.getStk().isEmpty()) {
            oneStep(prg);
            //here you can display the prg state
        }
    }

    something displayState(maybesomething){
        //display the current program state. You may want to display the program state after each
        //execution step if the display flag is set to on.
    }

}
