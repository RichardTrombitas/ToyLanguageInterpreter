package Model.Statements;

import Model.Data.IFileTable;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class CloseRFileStmt implements IStmt {
    private Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Close file: " + exp.toString();
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

        if (!ft.isDefined((StringValue) val)) {
            throw new MyException("There is no entry associated to this value in the FileTable!");
        }

        BufferedReader br = ft.lookup((StringValue) val);
        br.close();

        ft.delete((StringValue) val);

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

