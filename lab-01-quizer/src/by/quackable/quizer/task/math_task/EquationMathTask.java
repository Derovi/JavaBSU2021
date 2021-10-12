package by.quackable.quizer.task.math_task;

import by.quackable.quizer.task.Operator;
import by.quackable.quizer.task.Result;
import by.quackable.quizer.task.Task;
import by.quackable.quizer.task.math_task.AbstractMathTask;
import by.quackable.quizer.task_generator.math_task_generator.MathTaskGenerator;

import java.util.EnumSet;

public class EquationMathTask extends AbstractMathTask {
    public EquationMathTask(int precision, double number1, double number2, Operator operator) {
        super(operator, number1, number2, precision);
        if (!isValid(precision, number1, number2, operator)) {
            throw new RuntimeException("");
        }
    }

    public EquationMathTask(double number1, double number2, Operator operator) {
        super(operator, number1, number2, 0);
        if (!isValid(precision, number1, number2, operator)) {
            throw new RuntimeException("");
        }
    }

    static public boolean isValid(int precision, double number1, double number2, Operator operator) {
        if (precision == 0) {
            if (operator == Operator.MULTIPLY) {
                return number1 != 0 && number2 % number1 == 0;
            } else if (operator == Operator.DIVIDE) {
                return number2 != 0 && number1 % number2 == 0;
            }
            return true;
        } else {
            if (operator == Operator.MULTIPLY) {
                return Double.compare(number1, 0) != 0;
            } else if (operator == Operator.DIVIDE) {
                return Double.compare(number2, 0) != 0;
            }
        }
        return true;
    }

    @Override
    public String getAnswer() {
        double answer;
        switch (this.operator) {
            case DIVIDE -> answer = number1 / number2;
            case SUM -> answer = number2 - number1;
            case MULTIPLY -> answer = number2 / number1;
            case SUBTRACT -> answer = number1 - number2;
            default -> throw new IllegalStateException("Unexpected operator: " + this.operator);
        }
        return this.doubleToString(this.roundDouble(answer));
    }

    protected String getText(Object object1, Object object2) {
        return String.format("%s %s %s = %s", object1, this.operator.asString(), "x", object2);
    }

    @Override
    public String getText() {
        return this.getText(this.doubleToString(number1), this.doubleToString(number2));
    }

    public static class Generator extends MathTaskGenerator {
        public Generator(int precision, double min, double max, EnumSet<Operator> operations) {
            super(precision, min, max);
            this.operations = operations;
        }

        public Generator(double min, double max, EnumSet<Operator> operations) {
            super(0, min, max);
            this.operations = operations;
        }

        @Override
        public Task generate() {
            double number1 = this.getRandomDouble();
            double number2 = this.getRandomDouble();
            Operator operator = Operator.getRandomOperator(operations);
            while (!EquationMathTask.isValid(precision, number1, number2, operator)) {
                number1 = this.getRandomDouble();
                number2 = this.getRandomDouble();
            }
            return new EquationMathTask(precision, number1, number2, operator);
        }

        private final EnumSet<Operator> operations;
    }
}
