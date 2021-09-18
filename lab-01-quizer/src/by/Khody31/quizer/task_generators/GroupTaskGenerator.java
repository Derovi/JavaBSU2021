package by.Khody31.quizer.task_generators;

import by.Khody31.quizer.Task;

import java.util.Arrays;
import java.util.List;

public class GroupTaskGenerator implements Task.Generator {
    public GroupTaskGenerator(Task.Generator... generators) {
        this.generators = Arrays.stream(generators).toList();
    }

    public GroupTaskGenerator(List<Task.Generator> generators) {
        this.generators = generators;
    }

    @Override
    public Task generate() {
        return generators.get((int) (Math.random() * (generators.size()))).generate();
    }

    private final List<Task.Generator> generators;
}