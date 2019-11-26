package Model;

import Model.CollectionInstances.*;
import Model.Statements.IStmt;


public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutputList out;
    private IFileTable fileTable;

    //private IStmt originalProgram; //optional field, but good to have

    public PrgState(IExeStack stk, ISymTable symtbl, IOutputList ot, IStmt prg, IFileTable ft) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        //originalProgram = deepCopy(prg);    //recreate the entire original prg
        stk.push(prg);
    }

    public IExeStack getStk() {
        return exeStack;
    }
    public void setStk(IExeStack stk) {
        exeStack = stk;
    }

    public ISymTable getSymTbl() {return symTable;}
    public void setTbl(ISymTable symtbl) {
        symTable = symtbl;
    }

    public IOutputList getOutList() {return out;}
    public void setOutList(IOutputList ot) {
        out = ot;
    }

    public IFileTable getFileTable() {return fileTable;}
    public void setFileTable(IFileTable ft) {
        fileTable = ft;
    }

    @Override
    public String toString(){
        return exeStack.toString() + "\n" + symTable.toString() + "\n" + out.toString() + "\n";
    }
}
