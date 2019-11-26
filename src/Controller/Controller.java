package Controller;

import Model.Collections.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepository;

import java.io.IOException;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo, boolean displayFlag){
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    private void oneStep(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk=state.getStk();
        if(stk.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
    }

    public void allStep() throws MyException, IOException {
        PrgState prg = repo.getCrtPrg();

        if(displayFlag) System.out.println(prg.toString()+'\n');
        repo.logPrgStateExec();

        while (!prg.getStk().isEmpty()){
            oneStep(prg);
            if(displayFlag) System.out.println(prg.toString()+'\n');
            repo.logPrgStateExec();
        }
    }

    public void enableDisplayFlag(){this.displayFlag = true;}
    public void disableDisplayFlag(){this.displayFlag = false;}

}
