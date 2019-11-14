package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{

    private boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean equals(BoolValue another) {
        return val == another.getVal();
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    public Type getType() {
        return new BoolType();
    }
}