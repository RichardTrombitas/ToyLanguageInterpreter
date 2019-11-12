package Repository;

import Model.DataStructures.MyList;
import Model.PrgState;

public class Repository implements IRepository{

    private MyList<PrgState> prgStates = new MyList<>();
    public Repository(PrgState s){
        prgStates.add(s);
    }
    public PrgState getCrtPrg() {
        return prgStates.getFromIdx(0);
    }

}
