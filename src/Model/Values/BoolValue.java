package Model.Values;

import Model.MyException;
import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value {

    private boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public boolean equals(Value another) throws MyException {
        if(!another.getType().equals(new BoolType())){
            throw new MyException("The second value is not of BoolType!");
        }

        return val == ((BoolValue) another).getVal();
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

    public BoolValue copy() {
        return new BoolValue(val);
    }
}