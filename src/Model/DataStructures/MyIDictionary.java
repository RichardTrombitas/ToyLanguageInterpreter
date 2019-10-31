package Model.DataStructures;

import Model.Values.Value;

public interface MyIDictionary<T1, T2> {
    boolean isDefined(T1 id);

    T2 lookup(T1 id);

    void update(T1 id, T2 val);


}
