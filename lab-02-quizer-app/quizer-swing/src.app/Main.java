import by.polchernikova.quizer.Quiz;
import by.polchernikova.quizer.task.math_tasks.EquationMathTask;
import by.polchernikova.quizer.task.math_tasks.ExpressionMathTask;
import by.polchernikova.quizer.task.math_tasks.MathTask;
import by.polchernikova.quizer.task_generators.GroupTaskGenerator;
import by.polchernikova.quizer.task_generators.PoolTaskGenerator;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<String, Quiz>();
        EquationMathTask.Generator easy_equation_gen = new EquationMathTask.Generator(1, 5, EnumSet.of(MathTask.Operation.SUM, MathTask.Operation.DIFFERENCE), 0);
        ExpressionMathTask.Generator easy_expression_gen = new ExpressionMathTask.Generator(1, 5, EnumSet.of(MathTask.Operation.SUM, MathTask.Operation.DIFFERENCE), 0);
        quizMap.put("EASY TEST", new Quiz(new GroupTaskGenerator(easy_equation_gen, easy_expression_gen), 5));

        EquationMathTask.Generator hard_equation_gen = new EquationMathTask.Generator(-4.0, 9.0, EnumSet.allOf(MathTask.Operation.class), 1);
        ExpressionMathTask.Generator hard_expression_gen = new ExpressionMathTask.Generator(-9.0, 4.0,  EnumSet.allOf(MathTask.Operation.class), 1);
        quizMap.put("HARD TEST", new Quiz(new GroupTaskGenerator(hard_equation_gen, hard_expression_gen), 10));

        quizMap.put("SENSELESS PRECISION TEST", new Quiz(new EquationMathTask.Generator(-5, 5,  EnumSet.allOf(MathTask.Operation.class), 10), 5));

        ExpressionMathTask task1 = new ExpressionMathTask(0, 2, "+", 0);
        ExpressionMathTask task2 = new ExpressionMathTask(1, 1, "+", 0);
        ExpressionMathTask task3 = new ExpressionMathTask(2, 0, "+", 0);
        ExpressionMathTask task4 = new ExpressionMathTask(3, -1, "+", 0);
        ExpressionMathTask task5 = new ExpressionMathTask(4, -2, "+", 0);
        ExpressionMathTask rtask1 = new ExpressionMathTask(0.9, 2.9, "+", 1);
        ExpressionMathTask rtask2 = new ExpressionMathTask(1.9, 1.9, "+", 1);
        ExpressionMathTask rtask3 = new ExpressionMathTask(2.9, 0.9, "+", 1);
        ExpressionMathTask rtask4 = new ExpressionMathTask(3.9, -1.9, "+", 1);
        ExpressionMathTask rtask5 = new ExpressionMathTask(4.9, -2.9, "+", 1);
        quizMap.put("POOL TEST WITH DUPLICATES", new Quiz(new PoolTaskGenerator(true, task1, task2, task3, task4, task5, rtask1, rtask2, rtask3, rtask4, rtask5), 10));
        quizMap.put("POOL TEST WITHOUT DUPLICATES", new Quiz(new PoolTaskGenerator(false, task1, task2, task3, task4, task5, rtask1, rtask2, rtask3, rtask4, rtask5), 10));

        return quizMap;
    }
    public static void main(String[] args) {
        QuizerGUIWelcomeScreen app = new QuizerGUIWelcomeScreen(getQuizMap());
        app.setVisible(true);
    }
}
