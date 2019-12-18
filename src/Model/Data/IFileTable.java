package Model.Data;

import Model.Values.StringValue;

import java.io.BufferedReader;

public interface IFileTable {
    boolean isDefined(StringValue id);

    BufferedReader lookup(StringValue id);

    void update(StringValue id, BufferedReader val);

    void delete(StringValue id);

    String toStringSpecial();
}
