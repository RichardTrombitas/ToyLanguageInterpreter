package Model.Statements;

import Model.CollectionInstances.IHeap;
import Model.CollectionInstances.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class WriteHeapStmt implements IStmt {
    private String varName;
    private Exp exp;

    public WriteHeapStmt(String varName, Exp exp){
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "wH("+varName+","+exp.toString()+")";
    }

    public void execute(PrgState state) throws MyException {
        IHeap hp = state.getHeap();
        ISymTable st = state.getSymTbl();
        if(!st.isDefined(varName)) {
            throw new MyException(varName + " is not a variable in the SymTable!");
        }
        Value val = st.lookup(varName);
        Type varType = val.getType();
        if(!(varType instanceof RefType)){
            throw new MyException("The type of "+varName+" is not RefType!");
        }
        int addr = ((RefValue) val).getAddr();
        if(!(hp.isDefined(addr))){
            throw new MyException("There is no value at address "+Integer.toString(addr)+" on the heap!");
        }

        Value evalValue = exp.eval(st, hp);
        Type evalValueType = evalValue.getType();
        if(!(((RefType) varType).getInner().equals(evalValueType))){
            throw new MyException(varType.toString()+" and "+evalValueType.toString()+" are not equal types!");
        }

        hp.update(addr, evalValue);

    }
}
