package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Task;

import java.util.EnumSet;

public class ExpressionTask extends AbstractMathTask {
    static public class Generator extends AbstractMathTask.Generator {
        public Generator(double minNumber, double maxNumber, EnumSet<Operator> operators, int precision) {
            super(minNumber, maxNumber, operators, precision);
        }

        @Override
        public Task generate() {
            ExpressionTask task = null;
            while (task == null || task.hasDivisionByNull()) {
                task = new ExpressionTask(
                        getRandomBetweenMinAndMax(), getRandomBetweenMinAndMax(),
                        getRandomOperator(),
                        this.precision);
            }
            return task;
        }
    }

    public ExpressionTask(double firstOperand, double secondOperand, Operator op, int precision) {
        super(firstOperand, secondOperand, op, precision);
    }

    @Override
    public String getText() {
        return numberAsStringWithPrecision(firstOperand) + " " + op.getSymbol() + " ? = "
                + numberAsStringWithPrecision(getEquationResult());
    }

    @Override
    public double getCorrectAnswer() {
        return this.secondOperand;
    }
}
