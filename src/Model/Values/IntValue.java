package Model.Values;

import Model.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {

    private int val;

    public IntValue(int v) {
        val = v;
    }

    public boolean equals(Value another) throws MyException {
        if(!another.getType().equals(new IntType())){
            throw new MyException("The second value is not of IntType!");
        }

        return val == ((IntValue) another).getVal();
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

    public IntValue copy() {
        return new IntValue(val);
    }
}
