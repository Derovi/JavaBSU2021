package by.kmekhovich.task_generators;

import by.kmekhovich.quizer.Task;
import by.kmekhovich.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements TaskGenerator {
    boolean allowDuplicate;
    ArrayList<Task> tasks = new ArrayList<>();

    public PoolTaskGenerator(boolean allowDuplicate,
                             Task... tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks.addAll(Arrays.asList(tasks));
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
