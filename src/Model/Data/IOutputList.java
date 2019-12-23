package Model.Data;

import Model.Values.Value;

import java.util.List;

public interface IOutputList {
    void add(Value elem);

    String toStringSpecial();

    Value getFromIdx(int i);

    void setAtIdx(int i, Value elem);

    List<Value> getList();

    void setList(List<Value> list);
}
