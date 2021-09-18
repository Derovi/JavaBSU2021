package by.Khody31.quizer.task_generators;

import by.Khody31.quizer.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoolTaskGenerator implements Task.Generator {
    public PoolTaskGenerator(boolean isDuplicateAllowed, Task... tasks) {
        pool = new ArrayList<>(Arrays.stream(tasks).toList());
        this.isDuplicateAllowed = isDuplicateAllowed;
    }

    public PoolTaskGenerator(boolean allowDuplicate,
                             List<Task> tasks) {
        pool = new ArrayList<Task>(tasks);
        this.isDuplicateAllowed = allowDuplicate;
    }

    @Override
    public Task generate() {
        int idx = (int) (Math.random() * pool.size());
        if (isDuplicateAllowed) {
            return pool.get(idx);
        }
        return pool.remove(idx);
    }

    private final ArrayList<Task> pool;
    private final boolean isDuplicateAllowed;
}
