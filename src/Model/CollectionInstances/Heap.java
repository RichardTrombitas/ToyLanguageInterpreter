package Model.CollectionInstances;

import Model.Collections.MyDictionary;
import Model.Values.Value;

public class Heap implements IHeap {
    private MyDictionary<Integer, Value> dictionary = new MyDictionary<>();
    private int freeLocation = 0;

    public void allocate(Value val){
        dictionary.update(++freeLocation, val);
    }

    public void deallocate(Integer addr){
        dictionary.delete(addr);
    }

    public Value lookup(Integer addr) {
        return dictionary.lookup(addr);
    }

    public void update(Integer addr, Value val) {
        dictionary.update(addr, val);
    }

    @Override
    public String toString(){
        return dictionary.toString();
    }

    public String toStringSpecial() {
        return dictionary.toStringSpecial();
    }

}
