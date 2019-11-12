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
    private String logFilePath="log.txt";
    public Repository(PrgState s){
        prgState = s;
    }
    public PrgState getCrtPrg() {
        return prgState;
    }

    public void logPrgStateExec() throws IOException {
    //TODO
        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write("ExeStack:\n");
        logFile.write(prgState.getStk().toString()+"\n");
        logFile.write("SymTable:\n");
        logFile.write("\n");
        logFile.write("Out:\n");
        logFile.write("\n");
        logFile.write("FileTable:\n");
        logFile.write("_______________________________\n");
        logFile.close();
    }
}
