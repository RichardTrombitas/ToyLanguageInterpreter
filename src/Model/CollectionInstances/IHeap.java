package Model.CollectionInstances;

import Model.Values.Value;

public interface IHeap {
    int allocate(Value val);
    void deallocate(Integer addr);
    Value lookup(Integer addr);
    void update(Integer addr, Value val);
    boolean isDefined(Integer addr);
    String toStringSpecial();
}
