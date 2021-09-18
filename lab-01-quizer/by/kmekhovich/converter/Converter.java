package by.kmekhovich.converter;

import by.kmekhovich.operator.Operator;

public class Converter {
    public static char operatorToSymbol(Operator op) {
        return switch (op) {
            case PLUS -> '+';
            case MINUS -> '-';
            case MULTIPLICATION -> '*';
            case DIVISION -> '/';
        };
    }
}
