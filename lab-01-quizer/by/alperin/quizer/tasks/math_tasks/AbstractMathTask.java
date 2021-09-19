package by.alperin.quizer.tasks.math_tasks;

import by.alperin.quizer.Result;
import by.alperin.quizer.exceptions.EmptyOperationsList;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractMathTask implements MathTask {
    protected final double firstArgument;
    protected final double secondArgument;
    protected final int precision;

    public AbstractMathTask(double firstArgument,
                            double secondArgument,
                            int precision) {
        this.precision = precision;
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    protected abstract double getCorrectAnswer();

    @Override
    public Result validate(String answer) {
        try {
            if (Math.abs(Double.parseDouble(answer) - getCorrectAnswer()) <= ((precision != 0) ? Math.pow(10, -precision) : 0)) {
                return Result.OK;
            } else {
                return Result.WRONG;
            }
        } catch (final NumberFormatException | NullPointerException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    public static abstract class Generator implements MathTask.Generator {
        private final double minNumber;
        private final double maxNumber;
        protected final EnumSet<Operation> operations;
        protected final int precision;

        public Generator(double minNumber,
                         double maxNumber,
                         EnumSet<Operation> operations,
                         int precision) {
            if (maxNumber < minNumber) {
                throw new IllegalArgumentException("max number < min number");
            }

            if (operations.isEmpty()) {
                throw new EmptyOperationsList("Operations list mustn't be an empty.");
            }

            if (precision < 0) {
                throw new IllegalArgumentException("Precision must be positive.");
            }

            if (precision > 12) {
                throw new IllegalArgumentException("Large precision.");
            }

            this.precision = precision;
            this.minNumber = minNumber;
            this.maxNumber = maxNumber;
            this.operations = operations;
        }

        protected double precisionConverter(double number) {
            long roundedNumber = (long) (number * Math.pow(10, precision));
            return (double) roundedNumber / Math.pow(10, precision);
        }

        protected double getRandomDivisor(double lhs) {
            ArrayList<Double> divisors = new ArrayList<>();
            for (double divisor = precisionConverter(minNumber); divisor <= precisionConverter(maxNumber); ++divisor) {
                if (divisor == 0) {
                    continue;
                }

                if (lhs % divisor == 0) {
                    divisors.add(divisor);
                }
            }
            return divisors.get(ThreadLocalRandom.current().nextInt(divisors.size()));
        }

        protected double getNumberHelper(double lhs) {
            double rhs;
            do {
                rhs = getRandomNumber();
            } while (rhs == 0.0);

            if (lhs != 0.0 && precision == 0) {
                rhs = getRandomDivisor(lhs);
            }
            return rhs;
        }

        protected double getRandomNumber() {
            return precisionConverter(ThreadLocalRandom.current().nextDouble(getDiffNumber() + ((precision == 0) ? 1 : 0)) + getMinNumber());
        }

        @Override
        public double getMinNumber() {
            return minNumber;
        }

        @Override
        public double getMaxNumber() {
            return maxNumber;
        }
    }
}
