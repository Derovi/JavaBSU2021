package by.polchernikova.quizer.task_generators.math_task_generators;

public class ExpressionMathTaskGenerator extends AbstractMathTaskGenerator {
    public ExpressionMathTaskGenerator (
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
