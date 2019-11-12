package Repository;
import Model.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCrtPrg();
    void logPrgStateExec() throws IOException;
}
