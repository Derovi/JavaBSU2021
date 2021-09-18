package by.kmekhovich.task_generators;

import by.kmekhovich.quizer.Task;
import by.kmekhovich.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements TaskGenerator {
    ArrayList<TaskGenerator> generators = new ArrayList<>();

    public GroupTaskGenerator(TaskGenerator... generators) {
        this.generators.addAll(Arrays.asList(generators));
    }

    public GroupTaskGenerator(ArrayList<TaskGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public Task generate() {
        int index = ThreadLocalRandom.current().nextInt(generators.size());
        return generators.get(index).generate();
    }
}
