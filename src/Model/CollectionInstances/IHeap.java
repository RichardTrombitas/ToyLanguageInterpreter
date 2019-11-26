package Model.CollectionInstances;

import Model.Values.Value;

import java.util.Map;

public interface IHeap {
    int allocate(Value val);
    void deallocate(Integer addr);
    Value lookup(Integer addr);
    void update(Integer addr, Value val);
    Map<Integer, Value> getContent();
    void setContent(Map<Integer, Value> map);
    boolean isDefined(Integer addr);
    String toStringSpecial();
}
