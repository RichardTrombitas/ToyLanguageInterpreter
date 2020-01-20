package Model.Values;

import Model.MyException;
import Model.Types.BoolType;
import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value {

    private String val;

    public StringValue(String v) {
        val = v;
    }

    public boolean equals(Value another) throws MyException {
        if(!another.getType().equals(new StringType())){
            throw new MyException("The second value is not of StringType!");
        }

        return val.equals(((StringValue) another).getVal());
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }

    public Type getType() {
        return new StringType();
    }

    public StringValue copy() {
        return new StringValue(val);
    }
}
