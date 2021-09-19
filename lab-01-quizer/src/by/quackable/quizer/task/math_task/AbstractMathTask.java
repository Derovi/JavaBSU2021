package by.quackable.quizer.task.math_task;

import by.quackable.quizer.task.Operator;
import by.quackable.quizer.task.Result;

abstract public class AbstractMathTask implements MathTask {
    AbstractMathTask(Operator operator, double number1, double number2, int precision) {
        this.operator = operator;
        this.precision = precision;
        this.number1 = number1;
        this.number2 = number2;
    }

    public Result validate(String answer) {
        String right_answer = this.getAnswer();
        if (precision == 0 && answer.contains(".")) {
            return Result.INCORRECT_INPUT;
        }
        if (precision > 0 && answer.length() - answer.indexOf('.') - 1 != getPrecision()) {
            return Result.INCORRECT_INPUT;
        }

        try {
            Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if (answer.equals(right_answer)) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    double roundDouble(double number) {
        double pow = Math.pow(10., getPrecision());
        return Math.round(number * pow) / pow;
    }

    String doubleToString(double number) {
        return String.format("%." + this.getPrecision() + "f", number);
    }

    int getPrecision() {
        return precision;
    }

    protected final double number1;
    protected final double number2;
    protected final int precision;
    protected final Operator operator;
}
