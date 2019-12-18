package Model.Statements;
import Model.Data.ISymTable;
import Model.MyException;
import Model.PrgState;
import Model.Types.*;

import java.util.Map;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;

    public VarDeclStmt(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString(){ return typ + " " + name; }

    public PrgState execute(PrgState state) throws MyException {

        ISymTable symTable = state.getSymTbl();
        if(symTable.isDefined(name)) {
            throw new MyException("Variable is already declared!");
        }
        else {
            if(typ instanceof BoolType)
            {
                symTable.update(name, new BoolType().defaultValue());
            }
            if(typ instanceof IntType)
            {
                symTable.update(name, new IntType().defaultValue());
            }
            if(typ instanceof RefType)
            {
                Type innerType = ((RefType) typ).getInner();
                symTable.update(name, new RefType(innerType).defaultValue());
            }
            if(typ instanceof StringType)
            {
                symTable.update(name, new StringType().defaultValue());
            }
        }
        return null;
    }

    public Map<String,Type> typecheck(Map<String,Type> typeEnv) throws MyException {
        typeEnv.put(name,typ);
        return typeEnv;
    }
}
