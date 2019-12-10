package Controller;

import Model.Data.IHeap;
import Model.PrgState;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    private Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, IHeap heap) {
        Map<Integer, Value> map = heap.getContent().entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<Integer, Value> newMap = new HashMap<>(map);
        for (Map.Entry<Integer, Value> mapElement : map.entrySet()) {
            newMap.putAll(heap.getReachableValues(mapElement.getKey()));
        }
        return newMap;
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        repo.setPrgList(prgList);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while (prgList.size() > 0) {
            IHeap hp = prgList.get(0).getHeap();

            List<Integer> addressList = new ArrayList<>();
            prgList.forEach(prg -> addressList.addAll(getAddrFromSymTable(prg.getSymTbl().getContent().values())));
            hp.setContent(garbageCollector(addressList, hp));

            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }


}

