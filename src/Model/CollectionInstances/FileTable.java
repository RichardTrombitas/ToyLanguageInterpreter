package Model.CollectionInstances;

import Model.Collections.MyDictionary;
import Model.Values.StringValue;

import java.io.BufferedReader;

public class FileTable implements IFileTable {
    private MyDictionary<StringValue, BufferedReader> dictionary = new MyDictionary<>();

    public boolean isDefined(StringValue id) {
        return dictionary.isDefined(id);
    }

    public BufferedReader lookup(StringValue id) {
        return dictionary.lookup(id);
    }

    public void update(StringValue id, BufferedReader val) {
        dictionary.update(id, val);
    }

    public void delete(StringValue id) {
        dictionary.delete(id);
    }

    @Override
    public String toString(){
        return dictionary.toString();
    }

    @Override
    public String toStringSpecial() {
        return dictionary.toStringSpecial();
    }

}
