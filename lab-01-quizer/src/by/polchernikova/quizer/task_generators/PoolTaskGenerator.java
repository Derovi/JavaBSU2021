package by.polchernikova.quizer.task_generators;

import by.polchernikova.quizer.Task;

import java.util.*;

public class PoolTaskGenerator implements Task.Generator {
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
    ) {
        pool = new ArrayList<Task>(Arrays.stream(tasks).toList());
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
