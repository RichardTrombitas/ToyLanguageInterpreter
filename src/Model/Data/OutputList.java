package Model.Data;

import Model.Values.Value;

import java.util.ArrayList;
import java.util.List;

public class OutputList implements IOutputList {
    private List<Value> list = new ArrayList<>();

    public synchronized void add(Value elem) {
        list.add(elem);
    }

    public synchronized Value getFromIdx(int i) {
        return list.get(i);
    }

    public synchronized void setAtIdx(int i, Value elem) {
        list.set(i, elem);
    }

    public List<Value> getList() {
        return list;
    }

    public void setList(List<Value> list) {
        this.list = list;
    }

    @Override
    public synchronized String toString() {
        return "Output: " + list.toString();
    }

    @Override
    public synchronized String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Value el : list) {
            res.append(el.toString()).append("\n");
        }
        return res.toString();
    }

}

