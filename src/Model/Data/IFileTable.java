package Model.Data;

import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.Map;

public interface IFileTable {
    boolean isDefined(StringValue id);

    BufferedReader lookup(StringValue id);

    void update(StringValue id, BufferedReader val);

    Map<StringValue, BufferedReader> getContent();

    void delete(StringValue id);

    String toStringSpecial();
}
