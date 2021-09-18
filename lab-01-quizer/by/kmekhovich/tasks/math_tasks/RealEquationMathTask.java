package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.converter.Converter;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Result;

public class RealEquationMathTask extends EquationMathTask implements RealMathTask {

    double a, b;
    int precision;

    public RealEquationMathTask(int precision, double a, double b, Operator op) {
        this.precision = precision;
        this.a = a;
        this.b = b;
        this.op = op;
    }

    @Override
    public boolean hasDivisionByNull() {
        if (op == Operator.DIVISION && this.b == 0) {
            return true;
        }
        return false;
    }

    @Override
    public double getPrecisionInPower() {
        return Math.pow(10., -this.precision);
    }

    public double getResult() {
        return switch (this.op) {
            case PLUS -> this.a + this.b;
            case MINUS -> this.a - this.b;
            case MULTIPLICATION -> this.a * this.b;
            case DIVISION -> this.a / this.b;
        };
    }

    @Override
    public double getCorrectAnswer() {
        return this.b;
    }

    @Override
    public String getText() {
        return this.a + " " + Converter.operatorToSymbol(this.op) + " x = " + getResult();
    }
}
