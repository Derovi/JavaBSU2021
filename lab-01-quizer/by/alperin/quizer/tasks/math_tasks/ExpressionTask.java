package by.alperin.quizer.tasks.math_tasks;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class ExpressionTask extends AbstractMathTask {
    private final String text;
    private double correctAnswer;

    public ExpressionTask(double a, double b, Operation operation, int precision) {
        super(a, b, precision);
        this.text = String.valueOf(a) + ' ' + operation + ' ' + b + " = ?";

        switch (operation) {
            case SUM -> this.correctAnswer = a + b;
            case DIFFERENCE -> this.correctAnswer = a - b;
            case MULTIPLICATION -> this.correctAnswer = a * b;
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
        public ExpressionTask generate() {
            Operation[] operationsList = operations.toArray(new Operation[0]);
            int generatedIndex = ThreadLocalRandom.current().nextInt(operations.size());
            double lhs, rhs;

            if (precisionConverter(getMinNumber()) == 0.0 && precisionConverter(getMaxNumber()) == 0.0) {
                if (operations.size() == 1 && operations.contains(Operation.DIVISION)) {
                    throw new ArithmeticException("Division by zero.");
                }

                while (operationsList[generatedIndex] == Operation.DIVISION) {
                    generatedIndex = ThreadLocalRandom.current().nextInt(operations.size());
                }
            }

            lhs = getRandomNumber();
            if (operationsList[generatedIndex] == Operation.DIVISION) {
                rhs = getNumberHelper(lhs);
            } else {
                rhs = getRandomNumber();
            }
            return new ExpressionTask(lhs, rhs, operationsList[generatedIndex], precision);
        }
    }
}
