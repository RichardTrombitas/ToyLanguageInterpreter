package Model;

import Model.Data.*;
import Model.Statements.IStmt;

import java.io.IOException;


public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutputList out;
    private IFileTable fileTable;
    private IHeap heap;
    private int id;
    //private IStmt originalProgram;

    private static int code = 0;

    private synchronized static int generateCode() {
        PrgState.code = PrgState.code + 1;
        return code;
    }


    public PrgState(IExeStack stk, ISymTable symtbl, IOutputList ot, IStmt prg, IFileTable ft, IHeap hp) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        stk.push(prg);
        fileTable = ft;
        heap = hp;
        id = generateCode();
        //originalProgram = deepCopy(prg);
    }

    public IExeStack getStk() {
        return exeStack;
    }

    public void setStk(IExeStack stk) {
        exeStack = stk;
    }

    public ISymTable getSymTbl() {
        return symTable;
    }

    public void setTbl(ISymTable symtbl) {
        symTable = symtbl;
    }

    public IOutputList getOutList() {
        return out;
    }

    public void setOutList(IOutputList ot) {
        out = ot;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable ft) {
        fileTable = ft;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap hp) {
        heap = hp;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PrgState id: " + id + "\n" + exeStack.toString() + "\n" +
                symTable.toString() + "\n" + heap.toString() + "\n" + out.toString() + "\n";
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, IOException {
        if (exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        //System.out.println("\n now executing: " + crtStmt.toString());
        return crtStmt.execute(this);
    }
}
