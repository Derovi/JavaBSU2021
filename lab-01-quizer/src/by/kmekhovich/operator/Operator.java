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
}
