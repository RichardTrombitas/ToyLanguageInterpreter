package Model.CollectionInstances;

import Model.Collections.MyDictionary;
import Model.Values.Value;

public class Heap implements IHeap {
    private MyDictionary<Integer, Value> dictionary = new MyDictionary<>();
    private int endAddress = 0;

    public int allocate(Value val){
        dictionary.update(++endAddress, val);
        return endAddress;
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

    public boolean isDefined(Integer addr) {
        return dictionary.isDefined(addr);
    }

    @Override
    public String toString(){
        return "Heap: "+dictionary.toString();
    }

    public String toStringSpecial() {
        return dictionary.toStringSpecial();
    }

}
