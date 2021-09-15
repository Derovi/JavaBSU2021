package by.polchernikova.quizer.task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.TaskGenerator;
import java.util.*;

public class PoolTaskGenerator implements TaskGenerator {
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
    ) {
        List pool_list = Arrays.stream(tasks).toList();
        pool = new ArrayList<Task>(pool_list);
        allowDupl = allowDuplicate;
    }

    public PoolTaskGenerator(
            boolean allowDuplicate,
            List<Task> tasks
    ) {
        pool = new ArrayList<Task>(tasks);
        allowDupl = allowDuplicate;
    }

    public Task generate() {
        int index = (int)(Math.random() * (pool.size()));
        if(allowDupl) {
            return pool.get(index);
        } else {
            return pool.remove(index);
        }
    }

    private ArrayList<Task> pool;
    private final boolean allowDupl;
}
