package Model.Statements;

import Model.CollectionInstances.IFileTable;
import Model.CollectionInstances.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private Exp exp;

    public CloseRFileStmt(Exp exp){
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Close file: "+exp.toString();
    }

    @Override
    public void execute(PrgState state) throws MyException, IOException {
        IFileTable ft = state.getFileTable();
        ISymTable symTbl = state.getSymTbl();
        Value val = exp.eval(symTbl);
        if(!val.getType().equals(new StringType())) {
            throw new MyException("The type of the evaluated expression is not of StringType!");
        }

        if(!ft.isDefined((StringValue) val)){
            throw new MyException("There is no entry associated to this value in the FileTable!");
        }

        BufferedReader br = ft.lookup((StringValue) val);
        br.close();

        ft.delete((StringValue) val);
    }
}

