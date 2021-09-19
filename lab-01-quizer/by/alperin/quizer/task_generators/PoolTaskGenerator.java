package by.alperin.quizer.task_generators;

import by.alperin.quizer.Task;
import by.alperin.quizer.exceptions.EmptyTasksListException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements Task.Generator {
    private final ArrayList<Task> tasksList;
    private final boolean allowDuplicate;

    public PoolTaskGenerator(boolean allowDuplicate,
                             Task... tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasksList = new ArrayList<>(Arrays.asList(tasks));
    }

    public PoolTaskGenerator(boolean allowDuplicate,
                             Collection<Task> tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasksList = new ArrayList<>(tasks);
    }

    @Override
    public Task generate() {
        if (!allowDuplicate) {
            if (!tasksList.isEmpty()) {
                int generatedIndex = ThreadLocalRandom.current().nextInt(tasksList.size());
                Task generatedTask = tasksList.get(generatedIndex);
                tasksList.remove(generatedIndex);
                return generatedTask;
            } else {
                throw new EmptyTasksListException("Empty tasks list!");
            }
        }
        return tasksList.get(ThreadLocalRandom.current().nextInt(tasksList.size()));
    }
}
