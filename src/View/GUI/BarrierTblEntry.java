package View.GUI;

import javafx.util.Pair;

import java.util.List;

public class BarrierTblEntry {
    private Integer index;
    private Integer n;
    private List<Integer> prgStateIDs;

    public BarrierTblEntry(Integer index, Integer n, List<Integer> prgStateIDs) {
        this.index = index;
        this.n = n;
        this.prgStateIDs = prgStateIDs;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public List<Integer> getPrgStateIDs() {
        return prgStateIDs;
    }

    public void setPrgStateIDs(List<Integer> prgStateIDs) {
        this.prgStateIDs = prgStateIDs;
    }
}
