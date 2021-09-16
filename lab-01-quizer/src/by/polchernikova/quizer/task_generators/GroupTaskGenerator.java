package by.polchernikova.quizer.task_generators;

import by.polchernikova.quizer.Task;

import java.util.*;

public class GroupTaskGenerator implements Task.Generator {
    public GroupTaskGenerator(Task.Generator... generators) {
        gens = Arrays.stream(generators).toList();
    }

    public GroupTaskGenerator(List<Task.Generator> generators) {
        gens = generators;
    }

    public Task generate() {
        return gens.get((int) (Math.random() * (gens.size()))).generate();
    }

    private List<Task.Generator> gens;
}
