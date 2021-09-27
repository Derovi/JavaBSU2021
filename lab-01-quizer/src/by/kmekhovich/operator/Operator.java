package by.kmekhovich.operator;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String symbol;

    public String getSymbol() {
        return this.symbol;
    }

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public double doOperation(double firstOperand, double secondOperand) {
        return switch (this) {
            case PLUS -> firstOperand + secondOperand;
            case MINUS -> firstOperand - secondOperand;
            case MULTIPLICATION -> firstOperand * secondOperand;
            case DIVISION -> firstOperand / secondOperand;
        };
    }
}
