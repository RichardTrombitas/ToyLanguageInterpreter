package Model.Values;

import Model.MyException;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value {
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public boolean equals(Value another) throws MyException {
        if(!another.getType().equals(new RefType(locationType))){
            throw new MyException("The second value is not of RefType(" + locationType.toString() + "!");
        }

        RefType rt = new RefType(locationType);
        return ((RefValue) another).address == address;
    }

    public int getAddr() {
        return address;
    }

    @Override
    public String toString() {
        return "(" + address + "," + locationType.toString() + ")";
    }

    public Type getType() {
        return new RefType(locationType);
    }

    public RefValue copy() {
        return new RefValue(address, locationType);
    }
}

