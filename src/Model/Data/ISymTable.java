package Model.Data;

import Model.Values.Value;

import java.util.Map;

public interface ISymTable {
    boolean isDefined(String id);
    Value lookup(String id);
    void update(String id, Value val);
    void delete(String id);
    Map<String, Value> getContent();
    void setContent(Map<String, Value> map);
    ISymTable deepCopy();
    String toStringSpecial();
}
