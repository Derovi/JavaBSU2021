package by.polchernikova.quizer.task_generators.math_task_generators;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator {
    public EquationMathTaskGenerator (
            double minNumber,
            double maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision,
            int precision
    ) {
        super(minNumber, maxNumber, generateSum, generateDifference, generateMultiplication, generateDivision, precision);
    }
}
