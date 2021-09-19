package by.alperin.quizer.task_generators;

import by.alperin.quizer.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements Task.Generator {
    private final ArrayList<Task.Generator> tasksList;

    public GroupTaskGenerator(Task.Generator... generators) {
        tasksList = new ArrayList<>(Arrays.asList(generators));
    }

    public GroupTaskGenerator(Collection<Task.Generator> generators) {
        tasksList = new ArrayList<>(generators);
    }

    @Override
    public Task generate() {
        return tasksList.get(ThreadLocalRandom.current().nextInt(tasksList.size())).generate();
    }
}
