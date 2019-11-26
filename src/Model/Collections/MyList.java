package Model.Collections;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list  = new ArrayList<>();

    public void add(T elem){
        list.add(elem);
    }

    public T getFromIdx(int i){
        return list.get(i);
    }

    public void setAtIdx(int i, T elem){
        list.set(i, elem);
    }

    @Override
    public String toString(){
        return list.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for(T el : list){
            res.append(el.toString()).append("\n");
        }
        return res.toString();
    }
}
