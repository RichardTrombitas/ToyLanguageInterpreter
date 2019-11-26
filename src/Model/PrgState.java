package Model;

import Model.CollectionInstances.*;
import Model.Statements.IStmt;


public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutputList out;
    private IFileTable fileTable;
    private IHeap heap;

    //private IStmt originalProgram; //optional field, but good to have

    public PrgState(IExeStack stk, ISymTable symtbl, IOutputList ot, IStmt prg, IFileTable ft, IHeap hp) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        stk.push(prg);
        fileTable = ft;
        heap = hp;

        //originalProgram = deepCopy(prg);    //recreate the entire original prg
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
    public void setFileTable(IFileTable ft) { fileTable = ft; }

    public IHeap getHeap() {return heap;}
    public void setHeap(IHeap hp) {
        heap = hp;
    }

    @Override
    public String toString(){
        return exeStack.toString() + "\n" + symTable.toString() + "\n" + heap.toString() + "\n" + out.toString() + "\n";
    }
}
