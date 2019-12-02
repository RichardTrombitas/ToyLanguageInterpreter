package Repository;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    private List<PrgState> prgList;
    private String logFilePath;

    public Repository(PrgState s, String logFilePath){
        prgList = new ArrayList<>();
        prgList.add(s);
        this.logFilePath = logFilePath;
    }

    public List<PrgState> getPrgList() {
        return prgList;
    }

    public void setPrgList(List<PrgState> list) {
        prgList = list;
    }

    public void logPrgStateExec(PrgState prgState) throws IOException {
        PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write("PrgState id: "+prgState.getId()+"\n");
        logFile.write("ExeStack:\n");
        logFile.write(prgState.getStk().toStringSpecial()+"\n");
        logFile.write("SymTable:\n");
        logFile.write(prgState.getSymTbl().toStringSpecial()+"\n");
        logFile.write("Out:\n");
        logFile.write(prgState.getOutList().toStringSpecial()+"\n");
        logFile.write("FileTable:\n");
        logFile.write(prgState.getFileTable().toStringSpecial()+"\n");
        logFile.write("Heap:\n");
        logFile.write(prgState.getHeap().toStringSpecial()+"\n");
        logFile.write("_______________________________\n");
        logFile.close();
    }
}
