package Controller;

import Model.DataStructures.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Repository.IRepository;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo, boolean displayFlag){
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        if(stk.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = stk.pop();
        PrgState newPrgState = crtStmt.execute(state);
        if(displayFlag) System.out.println(newPrgState.toString()+'\n');
        return newPrgState;
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();

        if(displayFlag) System.out.println(prg.toString()+'\n');

        while (!prg.getStk().isEmpty()){
            oneStep(prg);
            if(displayFlag) System.out.println(prg.toString()+'\n');
        }
    }

    public void enableDisplayFlag(){this.displayFlag = true;}
    public void disableDisplayFlag(){this.displayFlag = false;}

}
