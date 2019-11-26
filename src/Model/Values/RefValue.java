package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public boolean equals(RefValue another) {
        RefType rt = new RefType(locationType);
        return another.address == address && rt.equals(another.getType());
    }

    public int getAddr() {return address;}
    public Type getType() { return new RefType(locationType);}
}
