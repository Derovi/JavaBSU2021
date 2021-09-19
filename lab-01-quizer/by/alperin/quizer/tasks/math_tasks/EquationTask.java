package by.alperin.quizer.tasks.math_tasks;

import by.alperin.quizer.exceptions.InfiniteSolutionsException;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class EquationTask extends AbstractMathTask {
    private final String text;
    private double correctAnswer;

    public EquationTask(double a, double b, Operation operation, int precision) {
        super(a, b, precision);
        this.text = String.valueOf(a) + ' ' + operation + " ? = " + b;

        switch (operation) {
            case SUM -> this.correctAnswer = b - a;
            case DIFFERENCE -> this.correctAnswer = a - b;
            case MULTIPLICATION -> this.correctAnswer = b / a;
            case DIVISION -> this.correctAnswer = a / b;
        }
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    protected double getCorrectAnswer() {
        return correctAnswer;
    }

    public static class Generator extends AbstractMathTask.Generator {
        public Generator(double minNumber,
                         double maxNumber,
                         EnumSet<Operation> operations) {
            this(minNumber, maxNumber, operations, 0);
        }

        public Generator(double minNumber,
                         double maxNumber,
                         EnumSet<Operation> operations,
                         int precision) {
            super(minNumber, maxNumber, operations, precision);
        }

        @Override
        public EquationTask generate() {
            Operation[] operationsList = operations.toArray(new Operation[0]);
            int generatedIndex = ThreadLocalRandom.current().nextInt(operations.size());
            double lhs, rhs;

            if (precisionConverter(getMinNumber()) == 0.0 && precisionConverter(getMaxNumber()) == 0.0) {
                if (operations.size() == 1 && operations.contains(Operation.DIVISION)) {
                    throw new ArithmeticException("Division by zero.");
                }

                if (operations.size() == 2 && operations.contains(Operation.DIVISION) && operations.contains(Operation.MULTIPLICATION)) {
                    throw new InfiniteSolutionsException("Wrong bounds or precision.");
                }

                while (operationsList[generatedIndex] == Operation.DIVISION
                        || operationsList[generatedIndex] == Operation.MULTIPLICATION) {
                    generatedIndex = ThreadLocalRandom.current().nextInt(operations.size());
                }
            }

            if (operationsList[generatedIndex] == Operation.MULTIPLICATION) {
                rhs = getRandomNumber();
                lhs = getNumberHelper(rhs);
            } else if (operationsList[generatedIndex] == Operation.DIVISION) {
                do {
                    lhs = getRandomNumber();
                    rhs = getRandomNumber();
                } while (lhs == 0.0 || rhs == 0.0);

                if (precision == 0) {
                    rhs = getRandomDivisor(lhs);
                }
            } else {
                lhs = getRandomNumber();
                rhs = getRandomNumber();
            }
            return new EquationTask(lhs, rhs, operationsList[generatedIndex], precision);
        }
    }
}