package by.quackable.quizer.task_generator;

import by.quackable.quizer.task.Task;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements TaskGenerator{
    PoolTaskGenerator(boolean allow_duplicate, Task... tasks) {
        this.tasks = List.of(tasks);
        this.allow_duplicate = allow_duplicate;
    }
    PoolTaskGenerator(boolean allow_duplicate, List<Task> tasks) {
        this.tasks = tasks;
        this.allow_duplicate = allow_duplicate;
    }

    @Override
    public Task generate() {
        if (tasks.isEmpty()) {
            throw new RuntimeException("Task list is empty");
        }
        Random random = ThreadLocalRandom.current();
        int index = random.nextInt(tasks.size());
        Task task = tasks.get(index);
        if (!allow_duplicate) {
            tasks.remove(index);
        }
        return task;
    }

    private final boolean allow_duplicate;
    private final List<Task> tasks;
}
