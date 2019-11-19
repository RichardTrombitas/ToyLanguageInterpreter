package Repository;

//import Model.DataStructures.MyList;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository{

//    private MyList<PrgState> prgStates = new MyList<>();
//    public Repository(PrgState s){
//        prgStates.add(s);
//    }
//    public PrgState getCrtPrg() {
//        return prgStates.getFromIdx(0);
//    }

    private PrgState prgState;
    private String logFilePath;
    public Repository(PrgState prgState, String logFilePath){
        this.prgState = prgState;
        this.logFilePath = logFilePath;
    }
    public PrgState getCrtPrg() {
        return prgState;
    }

    public void logPrgStateExec() throws IOException {
        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write("ExeStack:\n");
        logFile.write(prgState.getStk().toStringSpecial()+"\n");
        logFile.write("SymTable:\n");
        logFile.write(prgState.getTbl().toStringSpecial()+"\n");
        logFile.write("Out:\n");
        logFile.write(prgState.getOutList().toStringSpecial()+"\n");
        logFile.write("FileTable:\n");
        logFile.write("_______________________________\n");
        logFile.close();
    }
}
