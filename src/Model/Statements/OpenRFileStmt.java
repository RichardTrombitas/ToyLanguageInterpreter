package Model.Statements;

import Model.Data.IFileTable;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OpenRFileStmt implements IStmt {
    private Exp exp;

    public OpenRFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Open file: " + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        IFileTable ft = state.getFileTable();
        ISymTable symTbl = state.getSymTbl();
        IHeap hp = state.getHeap();

        Value val = exp.eval(symTbl, hp);
        if (!val.getType().equals(new StringType())) {
            throw new MyException("The type of the evaluated expression is not of StringType!");
        }
        if (ft.isDefined((StringValue) val)) {
            throw new MyException("The string value is already a key in the file table!");
        }

        BufferedReader br = new BufferedReader(new FileReader(((StringValue) val).getVal()));

        ft.update((StringValue) val, br);

        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new MyException("Expression is not of StringType!");
        }
    }
}
