package by.kmekhovich.quizer.tasks;

import by.kmekhovich.quizer.Result;

/**
 * Interface, который описывает одно задание
 */
interface Task {
    /*
     @return текст задания
     */
    String getText();

    /*
     * Проверяет ответ на задание и возвращает результат
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    Result validate(String answer);
}
