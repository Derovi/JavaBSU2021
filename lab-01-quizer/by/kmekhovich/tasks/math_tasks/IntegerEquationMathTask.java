package by.kmekhovich.tasks.math_tasks;

import by.kmekhovich.converter.Converter;
import by.kmekhovich.operator.Operator;
import by.kmekhovich.quizer.Result;

public class IntegerEquationMathTask extends EquationMathTask implements IntegerMathTask {

    int a, b;

    public IntegerEquationMathTask(int a, int b, Operator op) {
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

    public int getResult() {
        return switch (this.op) {
            case PLUS -> this.a + this.b;
            case MINUS -> this.a - this.b;
            case MULTIPLICATION -> this.a * this.b;
            case DIVISION -> this.a / this.b;
        };
    }

    @Override
    public int getCorrectAnswer() {
        return this.b;
    }

    @Override
    public String getText() {
        return this.a + " " + Converter.operatorToSymbol(this.op) + " x = " + getResult();
    }
}
