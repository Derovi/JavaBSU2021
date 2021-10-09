package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Task;

import java.util.EnumSet;

public class EquationTask extends AbstractMathTask {
    static public class Generator extends AbstractMathTask.Generator {
        public Generator(double minNumber, double maxNumber, EnumSet<Operator> operators, int precision) {
            super(minNumber, maxNumber, operators, precision);
        }

        @Override
        public Task generate() {
            EquationTask task = null;
            while (task == null || task.hasDivisionByNull()) {
                task = new EquationTask(
                        getRandomBetweenMinAndMax(), getRandomBetweenMinAndMax(),
                        getRandomOperator(),
                        this.precision);
            }
            return task;
        }
    }

    public EquationTask(double firstOperand, double secondOperand, Operator op, int precision) {
        super(firstOperand, secondOperand, op, precision);
    }

    @Override
    public String getText() {
        return numberAsStringWithPrecision(firstOperand) + " " + op.getSymbol() + " "
                + numberAsStringWithPrecision(secondOperand) + " = ?";
    }

    @Override
    public double getCorrectAnswer() {
        return getEquationResult();
    }
}
