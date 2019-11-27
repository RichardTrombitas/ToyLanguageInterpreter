package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Values.Value;

public class VarExp implements Exp {
    private String id;

    public VarExp(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return id;
    }

    @Override
    public Value eval(ISymTable tbl, IHeap hp) {
        return tbl.lookup(id);
    }
}
