package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.task.math_tasks.MathTask;

import java.util.EnumSet;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator {
    public EquationMathTaskGenerator (
            double minNumber,
            double maxNumber,
            EnumSet<MathTask.Operation> opers,
            int precision
    ) {
        super(minNumber, maxNumber, opers, precision);
    }
}
