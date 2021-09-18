package by.kmekhovich.task_generators.math_tasks_generators;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Task;
import by.kmekhovich.tasks.math_tasks.AbstractMathTask;
import by.kmekhovich.tasks.math_tasks.IntegerExpressionMathTask;
import by.kmekhovich.tasks.math_tasks.RealExpressionMathTask;

import java.util.EnumSet;

public class IntegerExpressionMathTaskGenerator extends IntegerMathTaskGenerator {
    public IntegerExpressionMathTaskGenerator(int minNumber, int maxNumber, EnumSet<Operator> operators) {
        super(minNumber, maxNumber, operators);
    }

    @Override
    public Task generate() {
        AbstractMathTask task = null;
        while (task == null || task.hasDivisionByNull()) {
            task = new IntegerExpressionMathTask(getRandomIntBetweenMinAndMax(), getRandomIntBetweenMinAndMax(),
                    getRandomOperator());
        }
        return task;
    }
}
