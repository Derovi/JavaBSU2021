package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Result;
import by.kmekhovich.quizer.Task;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractMathTask implements MathTask {
    static abstract class Generator implements MathTask.Generator {
        double minNumber;
        double maxNumber;
        int precision;
        EnumSet<Operator> operators;

        public Generator(double minNumber, double maxNumber, EnumSet<Operator> operators, int precision) {
            this.minNumber = minNumber;
            this.maxNumber = maxNumber;
            this.operators = operators;
            this.precision = precision;

            if (this.minNumber > this.maxNumber) {
                throw new IllegalArgumentException("Incorrect min and max");
            }
            if (this.precision < 0 || this.precision > 10) {
                throw new IllegalArgumentException("Unsupported precision");
            }
            if (this.operators.isEmpty()) {
                throw new IllegalArgumentException("Empty set of operators");
            }
        }

        @Override
        public double getMinNumber() {
            return minNumber;
        }

        @Override
        public double getMaxNumber() {
            return maxNumber;
        }

        protected Operator getRandomOperator() {
            int result = ThreadLocalRandom.current().nextInt(operators.size());
            return (Operator) operators.toArray()[result];
        }

        protected double getRandomBetweenMinAndMax() {
            return ThreadLocalRandom.current().nextDouble() * getDiffNumber() + this.minNumber;
        }
    }

    double firstOperand, secondOperand;
    Operator op;
    int precision;

    double makeDoubleAccordingPrecision(double x) {
        double precisionInPositivePower = Math.pow(10, this.precision);
        double precisionInNegativePower = Math.pow(10, -this.precision);
        return (double) ((int) (x * precisionInPositivePower)) * precisionInNegativePower;
    }

    public AbstractMathTask(double firstOperand, double secondOperand, Operator op, int precision) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.op = op;
        this.precision = precision;

        this.firstOperand = makeDoubleAccordingPrecision(this.firstOperand);
        this.secondOperand = makeDoubleAccordingPrecision(this.secondOperand);
    }

    public boolean hasDivisionByNull() {
        return op == Operator.DIVISION && secondOperand == 0;
    }

    @Override
    public double getPrecisionInPower() {
        return Math.pow(10., -this.precision);
    }

    protected double getEquationResult() {
        double eq_result = op.doOperation(firstOperand, secondOperand);
        return makeDoubleAccordingPrecision(eq_result);
    }

    String numberAsStringWithPrecision(double x) {
        return String.format("%." + precision + "f", x);
    }
}
