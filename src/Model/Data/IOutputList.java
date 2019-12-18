package Model.Data;

import Model.Values.Value;

public interface IOutputList {
    void add(Value elem);

    String toStringSpecial();

    Value getFromIdx(int i);

    void setAtIdx(int i, Value elem);
}
