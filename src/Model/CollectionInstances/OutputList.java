package Model.CollectionInstances;

import Model.Collections.MyList;
import Model.Values.Value;

public class OutputList implements IOutputList {
    private MyList<Value> list  = new MyList<>();

    public void add(Value elem){
        list.add(elem);
    }

    @Override
    public String toString(){
        return "Output: "+list.toString();
    }

    @Override
    public String toStringSpecial() { return list.toStringSpecial(); }

}

