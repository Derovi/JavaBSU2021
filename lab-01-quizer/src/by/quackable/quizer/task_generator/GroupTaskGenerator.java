package by.quackable.quizer.task_generator;

import by.quackable.quizer.task.Task;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements TaskGenerator {
    public GroupTaskGenerator(TaskGenerator... generators) {
        this.generators = List.of(generators);
    }
    public GroupTaskGenerator(List<TaskGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public Task generate() {
        Random random = ThreadLocalRandom.current();
        return generators.get(random.nextInt(generators.size())).generate();
    }

    private final List<TaskGenerator> generators;
}
