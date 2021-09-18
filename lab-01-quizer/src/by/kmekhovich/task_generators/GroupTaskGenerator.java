package by.kmekhovich.task_generators;

import by.kmekhovich.quizer.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements Task.Generator {
    ArrayList<Task.Generator> generators = new ArrayList<>();

    public GroupTaskGenerator(Task.Generator... generators) {
        this.generators.addAll(Arrays.asList(generators));
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
