package Model.CollectionInstances;

import Model.Values.Value;

public interface ISymTable {
    boolean isDefined(String id);
    Value lookup(String id);
    void update(String id, Value val);
    void delete(String id);
    String toStringSpecial();
}
