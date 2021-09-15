package by.polchernikova.quizer.task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.TaskGenerator;

import java.util.*;

public class GroupTaskGenerator implements TaskGenerator {
    public GroupTaskGenerator(TaskGenerator... generators) {
        gens = Arrays.stream(generators).toList();
    }

    public GroupTaskGenerator(List<TaskGenerator> generators) {
        gens = generators;
    }

    public Task generate() {
        return gens.get((int) (Math.random() * (gens.size()))).generate();
    }

    private List<TaskGenerator> gens;
}
