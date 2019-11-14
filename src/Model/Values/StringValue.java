package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value {

    private String val;

    public StringValue(String v) {
        val = v;
    }

    public boolean equals(StringValue another) {
        return val.equals(another.getVal());
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
}
