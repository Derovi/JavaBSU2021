package by.quackable.quizer.task_generator.math_task_generator;

import by.quackable.quizer.task.Task;
import by.quackable.quizer.task.TextTask;
import by.quackable.quizer.task_generator.math_task_generator.MathTaskGenerator;

public class AppleTaskGenerator extends MathTaskGenerator {
    public AppleTaskGenerator(String name1, String name2, int max) {
        super(0, max);
        this.name1 = name1;
        this.name2 = name2;
        this.max = max;
    }

    @Override
    public Task generate() {
        int number1 = this.getRandomInteger();
        int number2 = this.getRandomInteger();
        if (number1 < number2) {
            int n = number1;
            number1 = number2;
            number2 = n;
        }
        return new TextTask(
                String.format("%s had %s apples, %s took %s apples from %s. How many apples remain?",
                name1, number1, name2, number2, name1), Integer.toString(number1 - number2));
    }

    private final String name1;
    private final String name2;
    private final int max;
}
