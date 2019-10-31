package Model.Types;

public class BoolType implements Type {
    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolType) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "boolean";
    }
}