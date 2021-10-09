package by.kmekhovich.task_generators;

import by.kmekhovich.quizer.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements Task.Generator {
    List<Task.Generator> generators;

    public GroupTaskGenerator(Task.Generator... generators) {
        this.generators = List.of(generators);
    }

    public GroupTaskGenerator(ArrayList<Task.Generator> generators) {
        this.generators = generators;
    }

    @Override
    public Task generate() {
        int index = ThreadLocalRandom.current().nextInt(generators.size());
        return generators.get(index).generate();
    }
}
