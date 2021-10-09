package by.kmekhovich.task_generators;

import by.kmekhovich.quizer.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements Task.Generator {
    boolean allowDuplicate;
    List<Task> tasks;

    public PoolTaskGenerator(boolean allowDuplicate,
                             Task... tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = List.of(tasks);
    }

    public PoolTaskGenerator(boolean allowDuplicate,
                             ArrayList<Task> tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = tasks;
    }

    @Override
    public Task generate() {
        int index = ThreadLocalRandom.current().nextInt(tasks.size());
        Task task = tasks.get(index);
        if (!allowDuplicate) {
            tasks.remove(index);
        }
        return task;
    }
}
