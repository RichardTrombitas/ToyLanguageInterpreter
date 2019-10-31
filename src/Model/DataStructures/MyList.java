package Model.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list  = new ArrayList<T>();

    public void add(T elem){
        list.add(elem);
    }
}
