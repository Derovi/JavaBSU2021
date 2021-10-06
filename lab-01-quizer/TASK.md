# Описание

Вам предстоит разработать приложение для проверки знаний учеников. Будьте внимательны, “ученики” обязательно будут искать дыры в вашем приложении!

Приложение должно функционировать следующим образом. Учитель врывается в класс и решает провести плановую проверку знаний, большими буквами на доске пишет название теста. Обрадованные прекрасной новостью, ученики, в свою очередь, охотно открывают разработанное вами приложение и вводят в _CLI_ название теста. Затем они предположительно самостоятельно отвечают на все вопросы, и в конце им выводится оценка.

>Напомню, что в Java есть как абстрактные классы, так и интерфейсы. Разница между ними в том, что интерфейс не может иметь реализованные методы и переменные (исключение - default, static методы, о них ниже). Абстрактный класс - обычный класс, но может иметь абстрактные методы и нельзя создать объект этого класса. Для того, чтобы обозначить абстрактный метод в абстрактном классе, нужно перед ним написать abstract. В случае интерфейса перед объявлением методов никаких модификаторов (public, private, abstract и т. д.) ставить не надо. Любой класс может расширять (extends) только один базовый класс и реализовывать (implements) несколько интерфейсов.

# Архитектура приложения

>Всю работу ведите в пакете `by.<ваш ник>.quizer`
## Базовые элементы

### Result
```java
/**
 * [Такой комментарий к методу/классу/полю называется java-doc - распознается IDE как документация]
 * @see <a href="Java documentation guide">https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html</a>
 *
 * Enum, который описывает результат ответа на задание
 */
enum Result {
    OK, // Получен правильный ответ
    WRONG, // Получен неправильный ответ
    INCORRECT_INPUT // Некорректный ввод. Например, текст, когда ожидалось число
}
```

### Task

```java
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
```

### TaskGenerator

```java
/**
 * Interface, который описывает один генератор заданий
 */
interface TaskGenerator {
    /*
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    Task
     */
    Task generate();
}
```

### Quiz
```java
/**
 * Class, который описывает один тест
 */
class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) { 
        // ...
    }
    
    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        // ...
    }
    
    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных 
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        // ...
    }
    
    /**
     * @return завершен ли тест
     */
    bool isFinished() {
        // ...
    }
    
    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        // ...
    }
    
    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        // ...
    }
    
    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber {
        // ...
    }
    
    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов. 
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        // ...
    }
}
```

## Функция main()

### getQuizMap
Этот метод будет использоваться из `main()`, чтобы получить список доступных тестов. Создание всех тестов (`Quiz`) будет захардкожено в этом методе. 

```java
/**
 * @return тесты в {@link Map}, где
 * ключ     - название теста {@link String}
 * значение - сам тест       {@link Quiz}
 */
static Map<String, Quiz> getQuizMap() {
    // ...
}
```

>Вы можете возвращать любую реализацию `Map` (`HashMap`, `TreeMap` и т. д.), но обычно не имеет никакого смысла возвращать `TreeMap` там, где она скрывается из интерфейсом.

Создайте статический метод ```static Map<String, Quiz> getQuizMap```, который будет возвращать все доступные тесты. Ключ в Map - название теста, значение - объект класса Quiz. После реализации разных TaskGenerator’ов (см. ниже), добавьте в этот метод несколько различных тестов. 

### public static void main()

```java
public static void main() {
    // ...
}
```

>Напомню, что `public static void main()` - входная точка вашего приложения. 
	
#### Описание 

Для начала, получите список всех тестов с помощью статической функции `getQuizMap()` и выведите пользователю сообщение _“Введите название теста...”_. Затем получите объект `Quiz` по этому названию, если он есть, иначе попросите повторить попытку.

Пока тест на завершен, опрашивайте ученика (пользуйтесь методами реализованного вами класса `Quiz`). В конце выведите ему его отметку.

## Реализация Task и TaskGenerator
>Реализации `Task` следует расположить в пакете `tasks`, а реализации `TaskGenerator` в пакете `task_generators`.
	
`Task` и `TaskGenerator` - интерфейсы, теперь нужно сделать их реализации.
Все классы `*Task` реализуют интерфейс `Task`, а `*TaskGenerator` - интерфейс `TaskGenerator`.


### ExpressionTaskGenerator 
Генерирует примеры вида `<num1><operator><num2>=<answer>`. Например, `2*5=?`.

```java
class ExpressionTaskGenerator implements TaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    ExpressionTaskGenerator(
        int minNumber,
        int maxNumber,
        bool generateSum,
        bool generateDifference,
        bool generateMultiplication,
        bool generateDivision
    ) {
        // ...
    }
    
    /**
     * return задание типа {@link ExpressionTask}
     */
    ExpressionTask generate() {
        // ...
    }
}
```

### EquationTaskGenerator 
Генерирует уравнения вида `<num1><operator>x=<answer>` и `x<operator><num2>=<answer>`. Например, `x/2=6`.

```java
class EquationTaskGenerator implements TaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    EquationTaskGenerator(
        int minNumber,
        int maxNumber,
        bool generateSum,
        bool generateDifference,
        bool generateMultiplication,
        bool generateDivision
    ) {
        // ...
    }
    
    /**
     * return задание типа {@link EquationTask}
     */
    EquationTask generate() {
        // ...
    }
}
```

### GroupTaskGenerator 
`TaskGenerator`, который позволяет объединить несколько других `TaskGenerator`.

```java
class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     * 
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    GroupTaskGenerator(TaskGenerator… generators) {
        // ...
    }
    
    /**
     * Конструктор, который принимает коллекцию генераторов
     * 
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<Generator> generators) {
        // ...
    }
    
    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    Task generate() {
        // ...
    }
```

### PoolTaskGenerator 
`TaskGenerator`, который отдает задания из заранее заготовленного набора.

```java
class PoolTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     * 
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    PoolTaskGenerator(
        bool allowDuplicate,
        Task… tasks
    ) {
        // ...
    }
    
    /**
     * Конструктор, который принимает коллекцию заданий
     * 
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    PoolTaskGenerator(
        bool allowDuplicate,
        Collection<Task> tasks
    ) {
        // ...
    }
    
    /**
     * @return случайная задача из списка
     */
    Task generate() {
        // ...
    }
```

### TextTask

```java
/**
 * Задание с заранее заготовленным текстом. 
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
class TextTask implements Task {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    TextTask(
        String text,
        String answer
    ) {
        // ...
    }
    
    // ...
}
```

## Добавляем абстракций
Для всего в этом пункте следует сделать пакет `math_tasks` в пакете `tasks` и `math_task_generators` в пакете `task_genertors`.

Давайте теперь сделаем `ExpressionMathTask` и `EquationMathTask` двух видов - целочисленные (Integer) и десятично-дробные (Real). Для этого введем несколько более сложную систему абстракций. 

>Я опишу иерархию для `Task`, такую же нужно повторить для `TaskGenerator`

Сделайте интерфейс `MathTask`, который расширяет интерфейс `Task`. Сделайте абстрактный класс `AbstractMathTask`, который реализует интерфейс `MathTask`. Далее сделайте, чтобы классы `ExpressionMathTask` и `EquationMathTask` наследовались от `AbstractMathTask`.

Перенесите обшую логику из `ExpressionMathTask` и `EquationMathTask` в `AbstractMathTask`.

Добавьте в интерфейс MathTaskGenerator методы:
```java
int getMinNumber(); // получить минимальное число
int getMaxNumber(); // получить максимальное число
```

Добавьте в интерфейс `MathTaskGenerator` **default** метод:
```java
/**
 * @return разница между максимальным и минимальным возможным числом
 *//
default int getDiffNumber();
```
>**default** метод имеет реализацию прямо в интерфейсе, не может быть перегружен и может использовать другие методы интерфейса

## EnumSet★

Сейчас сигнатуры `ExpressionTaskGenerator` и `EquationTaskGenerator` выглядят не очень красиво, приходится передавать туда 4 була для каждого оператора. Сделайте `enum Operation` внутри интерфейса `MathTask` и передавайте в `ExpressionTaskGenerator` и `EquationTaskGenerator` вместо булов `EnumSet`.

>https://www.baeldung.com/java-enumset

## Generator как nested class в Task★

Сейчас вся иерархия `Task` дублируется и для `Generator` в отдельном пакете, это не очень хорошая практика, т. к. за таким кодом сложно следить. Давайте сделаем все `Generator`'ы внутреннеми классами в соответствующих `Task`. Например, вместо `ExpressionTaskGenerator` будет `ExpressionTask.Generator`. При этом `GroupTaskGenerator` и `PoolTaskGenerator` остануться в отдельном пакете, т.к. они не привязаны к конкретному типу задачи.

```java
interface Task {
    interface Generator  { /* ... */ }
    // ...
}

interface MathTask extends Task {
    interface Generator extends Task.Generator { /* ... */ }
    // ...
}

abstract class AbstractMathTask implements Task {
    abstract class Generator implements MathTask.Generator { /* ... */ }
    // ...
}

class ExpressionTask extends AbstractMathTask {
    class Generator extends AbstractMathTask.Generator { /* ... */ }
    // ...
}

class EquationTask extends AbstractMathTask {
    class Generator extends AbstractMathTask.Generator { /* ... */ }
    // ...
}
```

## Real ExpressionTask и EquationTask★
Сделайте, чтобы `ExpressionTaskGenerator` и `EquationTaskGenerator` работал с double вместо int. Так же нужно изменить сигнатуру в методах `getMinNumber()`, `getMaxNumnber()`, `getDiffNumber` интерфейса `MathTask`, чтобы они возвращали double.

Добавьте в `ExpressionTaskGenerator` и `EquationTaskGenerator` конструктор, который после `maxNumber` принимает еще `int precision` - количество знаков после запятой в генерируемых числах. В других конструкторах считается, что `precision = 0`, т.e. генерируются только целые числа.

>★★ Учитывайте precision еще и в ответе

## UML★★
В каком-нибудь онлайн UML-редакторе (если очень хочется, можно и в пеинте) сделайте схему с участием всех интерфейсов и классов. Это схему нужно приложить в AnyTask к ссылке на github.
## Добавляем исключения
Добавьте исключения везде, где это необходимо. Например, когда у `Quiz` вызывается метод `getMark()`, пока тест на завершен или в конструктор `*MathTask` `maxNumber` передается меньше, чем `minNumber`, в `precision` передается некорректное число и т.д. В случае некорректных `minNumber`, `maxNumber`, `precision` уместно использовать `IllegalArgumentException`, для раннего вызова `getMark()` стоит сделать свое исключение, назвать `QuizNotFinishedException`. Все свои исключения стоит создавать в пакете `exceptions`.
## Добавляем тесты (Quiz) и проверяем

Теперь добавьте в метод `getQuizMap()` тестов (минимум 5) и тщательно протестируйте приложение. Обязательно используйте все созданные классы, постарайтесь придумать свои `TaskGenerator`. Например, который генерирует задания вида _"У **A** было X яблок, он(она) подарил(а) **B** Y яблок. Сколько яблок осталось у **A**?"_.
