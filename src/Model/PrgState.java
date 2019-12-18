package Model;

import Model.Data.*;
import Model.Statements.IStmt;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutputList out;
    private IFileTable fileTable;
    private IHeap heap;
    private int threadID;
    private static AtomicInteger code = new AtomicInteger();

    public PrgState(IExeStack stk, ISymTable symtbl, IOutputList ot, IStmt prg, IFileTable ft, IHeap hp) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        stk.push(prg);
        fileTable = ft;
        heap = hp;
        threadID = code.incrementAndGet();
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

    public int getThreadID() {
        return threadID;
    }

    @Override
    public String toString() {
        return "PrgState id: " + threadID + "\n" + exeStack.toString() + "\n" +
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
