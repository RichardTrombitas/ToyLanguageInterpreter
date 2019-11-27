package Controller;

import Model.CollectionInstances.IExeStack;
import Model.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo, boolean displayFlag){
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    private void oneStep(PrgState state) throws MyException, IOException {
        IExeStack stk=state.getStk();
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

            prg.getHeap().setContent(unsafeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTbl().getContent().values()),
                    prg.getHeap().getContent()));

            if(displayFlag) System.out.println(prg.toString()+'\n');
            repo.logPrgStateExec();
        }
    }

}
