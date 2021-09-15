package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.Task;
import by.polchernikova.quizer.TaskGenerator;
import by.polchernikova.quizer.task.TextTask;
import java.util.*;
import java.lang.Math;

public abstract class EquationMathTaskGenerator extends AbstractMathTaskGenerator {
    public EquationMathTaskGenerator(
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        super(generateSum, generateDifference, generateMultiplication, generateDivision);
    }
}
