package by.polchernikova.quizer.task_generators.math_task_generators;

public interface MathTaskGenerator {
    public double getMinNumber();
    public double getMaxNumber();
    default double getDiffNUmber() {
        return (getMaxNumber() - getMinNumber());
    }
}
