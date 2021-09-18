package by.Khody31.quizer.tasks.math_tasks;

import by.Khody31.quizer.Result;
import by.Khody31.quizer.Task;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Vector;

public abstract class AbstractMathTask implements MathTask {
    static abstract protected class Generator implements MathTask.Generator {
        public Generator(
                double minNum,
                double maxNum,
                EnumSet<Operation> operators,
                int precision) {
            if (minNum > maxNum) {
                throw new IllegalArgumentException("Minimum number is not less then maximum");
            }
            if (operators.isEmpty()) {
                throw new IllegalArgumentException("No operations are allowed");
            }
            if (precision < 0) {
                throw new IllegalArgumentException("Negative precision is not possible");
            }
            this.minNum = minNum;
            this.maxNum = maxNum;
            operations = new Vector<>();
            for (Operation op : operators) {
                operations.add(op.symbol);
            }
            this.precision = precision;
        }

        @Override
        public double getMinNumber() {
            return minNum;
        }

        @Override
        public double getMaxNumber() {
            return maxNum;
        }

        protected Vector<String> operations;
        protected final double minNum;
        protected final double maxNum;
        protected final int precision;
    }

    public AbstractMathTask(double num1, double num2, String op, int precision) {
        this.precision = precision;
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
    }

    public Result validate(String answer) {
        String rightAnswer = getAnswer();
        if (Objects.equals(answer, rightAnswer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    protected int precision;
    protected double num1;
    protected double num2;
    protected String op;
}
