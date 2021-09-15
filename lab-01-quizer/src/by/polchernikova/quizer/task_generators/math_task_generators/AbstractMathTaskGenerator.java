package by.polchernikova.quizer.task_generators.math_task_generators;

import by.polchernikova.quizer.TaskGenerator;
import by.polchernikova.quizer.exceptions.NothingToGenerateException;

import java.util.Vector;

public abstract class AbstractMathTaskGenerator implements TaskGenerator {
    public AbstractMathTaskGenerator(
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        if(!generateDifference && !generateDivision && !generateSum && !generateMultiplication) {
            throw new NothingToGenerateException("No operations are allowed");
        }
        operations = new Vector<String>();
        if(generateSum) {
            operations.add("+");
        }
        if(generateDifference) {
            operations.add("-");
        }
        if(generateMultiplication) {
            operations.add("*");
        }
        if(generateDivision) {
            operations.add("/");
        }
    }
    protected Vector<String> operations;
}
