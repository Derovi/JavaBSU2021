package by.kmekhovich.task_generators.math_tasks_generators;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Task;
import by.kmekhovich.tasks.math_tasks.AbstractMathTask;
import by.kmekhovich.tasks.math_tasks.IntegerExpressionMathTask;
import by.kmekhovich.tasks.math_tasks.RealEquationMathTask;

import java.util.EnumSet;

public class RealEquationMathTaskGenerator extends RealMathTaskGenerator {
    public RealEquationMathTaskGenerator(int precision, double minNumber, double maxNumber, EnumSet<Operator> operators) {
        super(precision, minNumber, maxNumber, operators);
    }

    @Override
    public Task generate() {
        AbstractMathTask task = null;
        while (task == null || task.hasDivisionByNull()) {
            task = new RealEquationMathTask(this.precision,
                    getRandomDoubleBetweenMinAndMax(), getRandomDoubleBetweenMinAndMax(),
                    getRandomOperator());
        }
        return task;
    }
}