package by.Khody31.quizer.tasks.math_tasks;

import by.Khody31.quizer.Result;
import by.Khody31.quizer.Task;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Vector;

public abstract class AbstractMathTask implements MathTask {
    abstract protected class Generator implements MathTask.Generator {
        public Generator(
                double minNum,
                double maxNum,
                EnumSet<Operation> operators,
                int precision) {
            this.minNum = minNum;
            this.maxNum = maxNum;
            operations = new Vector<>();
            for(Operation op : operators) {
                operations.add(op.symbol);
            }
            maxPrecision = precision;
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
        protected final int maxPrecision;
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
