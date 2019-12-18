package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {

    private int val;

    public IntValue(int v) {
        val = v;
    }

    public boolean equals(IntValue another) {
        return val == another.getVal();
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    public Type getType() {
        return new IntType();
    }

    public IntValue copy() { return new IntValue(val); }
}
