package Model.Values;

import Model.MyException;
import Model.Types.Type;

public interface Value {
    Type getType();
    boolean equals(Value another) throws MyException;
    Value copy();
}
