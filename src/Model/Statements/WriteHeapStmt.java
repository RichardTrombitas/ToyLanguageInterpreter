package Model.Statements;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.util.Map;

public class WriteHeapStmt implements IStmt {
    private String varName;
    private Exp exp;

    public WriteHeapStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "wH(" + varName + "," + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IHeap hp = state.getHeap();
        ISymTable symTbl = state.getSymTbl();
        if (!symTbl.isDefined(varName)) {
            throw new MyException(varName + " is not a variable in the SymTable!");
        }
        Value val = symTbl.lookup(varName);
        Type varType = val.getType();
        if (!(varType instanceof RefType)) {
            throw new MyException("The type of " + varName + " is not RefType!");
        }
        int addr = ((RefValue) val).getAddr();
        if (!(hp.isDefined(addr))) {
            throw new MyException("There is no value at address " + addr + " on the heap!");
        }

        Value evalValue = exp.eval(symTbl, hp);
        Type evalValueType = evalValue.getType();
        if (!(((RefType) varType).getInner().equals(evalValueType))) {
            throw new MyException(((RefType) varType).getInner().toString() + " and " + evalValueType.toString() + " are not equal types!");
        }

        hp.update(addr, evalValue);

        return null;

    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        if(!typeEnv.containsKey(varName)){
            throw new MyException("The variable " + varName + " is not defined!");
        }
        Type typevar = typeEnv.get(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar instanceof RefType) {
            if ((((RefType) typevar).getInner().equals(typexp))) {
                return typeEnv;
            } else {
                throw new MyException("WriteHeap: right hand side and left hand side have different types!");
            }
        } else {
            throw new MyException("The variable" + varName + " is not of RefType!");
        }
    }
}
