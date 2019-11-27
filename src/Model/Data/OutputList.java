package Model.Data;

import Model.Values.Value;

import java.util.ArrayList;
import java.util.List;

public class OutputList implements IOutputList {
    private List<Value> list  = new ArrayList<>();

    public void add(Value elem){
        list.add(elem);
    }

    public Value getFromIdx(int i){
        return list.get(i);
    }

    public void setAtIdx(int i, Value elem){
        list.set(i, elem);
    }

    @Override
    public String toString(){
        return "Output: "+list.toString();
    }

    @Override
    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for(Value el : list){
            res.append(el.toString()).append("\n");
        }
        return res.toString();
    }

}

