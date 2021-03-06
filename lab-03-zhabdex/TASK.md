# Домашняя работа №3. Мониторинг сервисов Zhabdex

**Если что-то не понятно или не получается, пожалуйста, сообщите, чтобы вы не тратили в пустую много времени!**

**Если найдете ошибки/ опечатки в условии, захотите добавить примеров/ что-то дополнить, открывайте сюда Pull Request.**

В компании **Zhabdex** существует много сервисов. Вам, как стажеру этой немалоизвестной компании, поступила задача **написать систему для мониторинга внутренних сервисов.**

![Главный офис компании Zhabdex](https://i.ibb.co/Ny61qBP/aux-head-1597137473-202020811-zlobin-sahar-zawod-t.png)

Для выполнения этого сложного задания вам потребуется воспользоваться внутренним сервисом `zhabdex.ovi.by`, библиотеками `common` и `visualizer`

* `zhabdex.ovi.by` - сервис, который позволяет вам с помощью http запросов узнать информацию о других сервисах - uptime, rps и т.д.
* `visualizer` - библиотека, в недавнем прошлом написанная другим стажером из Германии. Она позволяет выводить табличную информацию в консоль. *Внимание, стажер переучивался с раста!*
* `common` - библиотека содержит тип Service и json-десериализатор.

В традициях компании Zhabdex документации по внутренним сервисам и библиотекам нет, поэтому в том как отправлять запросы к `zhabdex.ovi.by` и использовать библиотеку `common` вам придется разобраться самостоятельно

К сожалению, подробное ТЗ от вашего тимлида не прошло цензуру, поэтому во многом придется разбираться самостоятельно.

## Чего нужно добиться?

Вам нужно сделать консольное приложение, которое в таблицах будет показывать какую-то информацию о сервисах Zhabdex. Например, среднее количество запросов в секунду в каждом датацентре. Для этого вам нужно переодически отправлять http-запросы к сервису `zhabdex.ovi.by`, чтобы получить сырые данные о сервисах.

## Начинаем разработку
### MVP

Это самая сложная часть задания - сделать чтобы хоть что-то запускалось (Minimal Viable Product).

#### Цель
Реализовать приложение, которое будет отображать в таблице информацию о всех доступных сервисах zhabdex. Нужно раз в секунду отправлять запрос на сервис `zhabdex.ovi.by`, чтобы получить список всех доступных сервисов zhabdex и информацию о них. Отображать эту информацию нужно с помощью библиотеки `visualizer`.

У вас должно получиться примерно следующее:

![Zhabdex monitoring](
https://i.ibb.co/ZhXHhHt/zhabdex.gif)

#### Инструменты
* Библиотека `visualizer` для отрисовки таблиц
* Web-сервис `zhabdex.ovi.by` для того, чтобы получать информацию о сервисах в Json
* Библиотека `common`, которая:
  * Содержит класс Service, который можно использовать при десериализации полученного json
  * Подключает библиотеку Jackson
  * Имеет статический объект JSON класса ObjectMapper, который следует использовать для десериализации.
* Web-сервис `emulator`, который можно поднять самостоятельно и использовать для тестирования, расширить, улучшить, доработать.

#### Инструкция

Вести разработку следует в `.../lab-03-zhabdex/monitoring`, там уже создан gradle-проект и подключены зависимости от `common` и `visualizer`. Убедитесь, что классы из этих библиотек можно импортировать.
Код библиотек `common` и `visualizer`, а так же web-сервиса `emulator` лежит в директории `.../lab-03-zhabdex/zhabdex-repo`.
**Для того, чтобы получить информацию о сервисах zhabdex, нужно отправить GET запрос на `zhabdex.ovi.by/status`**. Результат будет получен в качестве списка Json-объектов. Этот список можно десериализвать в `List<Service>` с помощью объекта `JSON` из `common`. (Гуглите как это делается библиотекой Jackson)
Обязательно откройте в браузере http://zhabdex.ovi.by/status, чтобы увидеть как выглядят данные, пообновляйте страницу.
Как использовать `visualizer` следует разобраться самостоятельно.

#### Пример использования Visualizer
_Пожалуйста, контрибьютните =)_

#### Помощь

Удачи!

### О сервисе Emulator

`Emulator` используется, чтобы эмулировать поведение сервисов - запросы пользователей, частичное или полное падение сервиса и т.д.

Его исходный код лежит в директории `.../lab-03-zhabdex/zhabdex-repo/emulator`

#### Сборка и запуск

Все команды выполнять в директории `.../lab-03-zhabdex/zhabdex-repo`

`gradlew emulator:bootRun` - запустить сервер на порту 8080
`gradlew emulator:bootJar` - собрать jar-файл, результат будет лежать в `.../lab-03-zhabdex/zhabdex-repo/emulato/build/libs/emulator.jar`

#### Contributing в emulator

Если вы хотите как-то улучшить/ доработать emulator, сделайте для этого отдельную ветку и откройте pull request в мой (основной) master. После одобрения этого pull request, все смогут его использовать и он будет выкачен на `zhabdex.ovi.by`

Сливать эту ветку в свою ветку с разработкой основного приложения можно когда угодно.

За помощь в разработке `emulator` особая бладарность!

Идеи для доработки:
* Новые Event'ы, изменения в имеющихся.
* Добавить поддержку конфигураций. Например, чтобы вместо описывания всех сервисов в коде, можно было сделать это в json файле.
* Новые характеристики у сервиса. Можно придумать какую еще информацию можно предоставлять о сервисе. Тогда изменения еще нужно будет внести в класс Service в `common`.

#### Локальные и временные изменения

Если вы хотите внести какие-то изменения в emulator для себя при тестировании, которые не очень чистовые и вообще не особо хочется их коммитить, делайте это в ветке, где и идет основная разработка.

При этом не обязательно следить за тем, чтобы они не попали в коммит, можно спокойно открывать финальный пул реквест с вашими  черновыми изменениями в emulator, мне было бы интересно посмотреть.

### Библиотека для аггрегации данных

Мы работаем с каким-то множеством сервисов, который меняется со временем - каждый момент времени в этот список может добавится сервис ровно так же, как и исчезнуть из него в результате падения.

Хочется уметь решать следующие задачи:
* Отображать средний ping, RPS.
* Отображать топ 5 сервисов с самым большим uptime.
* Отображать суммарное количество запросов по датацентрам.
* И многое другое, для чего нужно как-то аггрегировать сырые данные...

Мы хотим разработать библиотеку, которая добавит "умные" коллекции. "Умные" значит, что они умеют несколько больше, чем просто хранить данные. А потом будем использовать эту библиотеку для решения задач, которые описаны выше.

Например:
* ```SortedCollection``` позволит итерироваться по элементам в порядке сортировки.
* ```FilteredCollection``` позволит итерироваться только по тем элементам, которые удовлетворяют предикату.
* ```MappedCollection``` каждому объекту сопоставит какой-то другой.
* ```LimitedCollection``` будет вести себя так, как буд-то у нас в коллекции всего N первых элементов.

Пока что кажется немного странным, но весело будет когда мы научимся объединять эти коллекции!


Рассмотрим несколько примеров использования этой библиотеки, которую нужно будет разработать. Пусть, например, нам нужно отображать сервисы в порядке, отсортированном по количеству запросов с момента включения сервиса.

```java
var sortedServices = new SortedCollection<>(Service::getRequestsForUptime);

while (true) {
    List<Service> services = fetchServices();
    sortedServices.renew(services);
    
    Thread.sleep(1000);    
}
```

Теперь нам нужно показывать только первые 5 таким сервисов

```java
var topServices = 
    new SortedCollection<>(Service::getRequestsForUptime) // Сортировка по кол-ву запросов
    .concat(new LimitedCollection<>(5)); // Только первые 5 сервисов

while (true) {
    List<Service> services = fetchServices();
    topServices.renew(services);
    
    Thread.sleep(1000);
    
    // Теперь выведем топ 5 сервисов по кол-ву запросов
    topServices.forEach(System.out::println)
}
```

#### Чем это отличается от обычного stream api?
* Мы лишь 1 раз задаем весь pipe-line обработки данных, а не делаем это каждый раз когда обновились данные. Иногда это очень удобно.
* Если данные обновляются не полностью, а частично, можно проводить некоторые оптимизации и не пересчитывать то, что не надо.
* Например, если мы считаем средний ping по датацентрам, у нас обновилась информация только о 4 сервисах из 1000, можно решить задачу 4x операций, а не 1000x.
* Эту систему коллекций можно расширять, когда в stream api ограниченный набор функций. 

### Базовые интерфейсы

Сделаем два базовых интерфейса:

```java
public interface FinalProcessedCollection<T, E> {
    void renew(Collection<? extends T> elements);
    E currentState();
}
```

```java

public interface ProcessedCollection<T, E> extends 
        FinalProcessedCollection<T, Collection<? extends E>> {}
```

Будем называть все наши "умные" коллекции `processed collection`. Для такого не очень просто придумать название, так что можете предлагать идеи.

##### Почему два разных интерфейса?
*****
```FinalProcessedCollection``` в качестве currentState() возвращает элемент ```E```, а ProcessedCollection - ```Collection<E>```. Например, ```MappedCollection implements ProcessedCollection```, когда ```ReducedCollection implements FinalProcessedCollection```. Вообще, кажется, только ```ReducedCollection``` будет реализовывать ```FinalProcessedCollection``` вместо ```ProcessedCollection```.

##### Почему такое название - **Final**ProcessableCollection?
*****
Идея в том, что результат ```ProcessedCollection``` можно обрабатывать дальше, а для ```FinalProcessedCollection``` это не имеет смысла.

##### Почему эти интерфейсы не расширяют Collection?
*****
В **Collection** есть много методов, который тут не имеют смысла. Например, **contains**, **removeAll** и т.д.

### Базовые коллекции

Нужно реализовать следующие коллекции:
* MappedCollection
* FilteredCollection
* ReducedCollection
* LimitedCollection
* ReversedCollection
* SortedCollection

Все, кроме ```ReducedCollection``` должны расширять ```ProcessedCollection```, а ```ReducedCollection``` - расширять```FinalProcessedCollection```.

#### MappedCollection
Каждому элементу в коллекции сопоставляет другой.

Конструктор принимает `Function mapper` - функцию, которая каждому элементу T исходной коллекции сопоставит некоторый элемент U.

##### Пример

```java
MappedCollection<Integer> collection 
        = new MappedCollection<>((Integer a) -> "Number is " + a.toString());

collection.renew(List.of(4, 5, 6, 7));
collection.currentState().forEach(System.out::println);

/**
Вывод:
    Number is 4
    Number is 5
    Number is 6
    Number is 7
*/
```

#### FilteredCollection
Из всех элементов остаются только удовлетворяющие предикату.

Конструктор принимает `Predicate predicate` - функция, которая принимает объект типа T и возвращает bool - должен ли этот элемент быть в коллекции.

##### Пример

```java
FilteredCollection<Integer> collection 
        = new FilteredCollection<>(value -> value > 5);

collection.renew(List.of(4, 5, 6, 7));
collection.currentState().forEach(System.out::println);

/**
Вывод:
    6
    7
*/
```

#### LimitedCollection

Конструктор принимает `long maxSize` - максимальное количество элементов в коллекции.

##### Пример

```java
LimitedCollection<Integer> collection
        = new LimitedCollection<>(2);

collection.renew(List.of(4, 5, 6, 7));
collection.currentState().forEach(System.out::println);

/**
Вывод:
    4
    5
*/
```

#### ReducedCollection

Конструктор принимает `BinaryOperator<T> reducer` - функция, которая принимает два аргумента и возвращает результат выполнения операции над ними. Например, для нахождения суммы всех чисел следует использовать `(x, y) -> x + y`.

Возникает вопрос: _Что возвращать, если коллекция пустая, ведь не ясно какой элемент брать за начальный?_
Поэтому следует в `currentState()` возвращать объект типа `Optional<T>` и если элементов в коллекции не было, это `Optional.empty()`

##### Пример

```java
ReducedCollection<Integer> collection 
        = new ReducedCollection<>(Integer::sum);

collection.renew(List.of(4, 5, 6, 7));
System.out.println(collection.currentState().get());

/**
Вывод:
    22
*/
```

##### Помощь
Скорее всего, вы попробовали вызвать функцию `reduce` из **streamapi** на *Collection<? extends T>* и передали туда оператор *BiOperator<? extends T>*. Обдумайте, почему это не может скомпилироваться и найдите решение. Проблема возникает при подстановке в реализацию `reduce` типа *? extends T*. Если сразу не очевидно, попробуйте реализовать свой *reduce* и найти в нем логическую ошибку при подстановке типа *? extends T*. После решения проблемы, оставьте там комментарий, который объясняет проблему и ее решение.

#### ReversedCollection

##### Пример

```java
ReversedCollection<Integer> collection = new ReversedCollection<>();

collection.renew(List.of(4, 5, 6, 7));
collection.currentState().forEach(System.out::println);

/**
Вывод:
    7
    6
    5
    4
*/
```

#### SortedCollection

* Конструктор 1 принимает `Comparator comparator`.
* Конструктор 2 принимаает `Function keyExtractor` - функцию, которая извлекает из исходного объекта ключ и сравнивает по нему (см. Пример 1)
* Конструктор 3 принимает `Function keyExtractor` и `bool reversed` - нужно ли сортировать в обратном порядке.

##### Пример 1

```java
SortedCollection<Service> collection 
        = new SortedCollection<>(Service::getNodesCount);

collection.renew(fetchServices());
collection.currentState().forEach(service -> System.out.println(service.getNodesCount()));

/**
Вывод:
    10
    27
    96
*/
```

##### Пример 2

```java
SortedCollection<Service> collection
        = new SortedCollection<>((first, second) -> second.getNodesCount() - first.getNodesCount());

collection.renew(fetchServices());
collection.currentState().forEach(service -> System.out.println(service.getNodesCount()));

/**
Вывод:
    96
    27
    10
*/
```

### Юнит тесты
Для всех коллекций сделайте хотя бы по несколько (одному) простых тестов.

### Объединение коллекций

Мы разработали крутые коллекции, но что, если нужно поддерживать суммарное количество Node в 3 сервисах с самым большим RPS? Для этого нужно научиться объединять коллекции.

В `ProcessedCollection` добавьте **default** функцию `compose`, которая будет принимать другой `ProcessedCollection` и возвращать их "объединенную версию". Обозначим эти коллекции как **A** и **B**, т. e. функция `compose` действут следующим образом: **A**.compose(**B**). Результатом этой функции должна быть коллекция **C**, которая имеет такой же вход, как **A**, а выход как **B**. Т.e. если бы мы рассматривали **A** и **B** как функции, то **C** была бы их композицией - **C(t) = B(A(t))**

Аналогичным образом добавьте в `ProcessedCollection` функцию `compose`, которая принимает `FinalProcessedCollection`.

**На FinalProcessedCollection вызвать функцию compose нельзя.**

##### Пример

Ищем суммарный rps в топ 3 сервисах по количеству node из датацентра "Chaplin".

```java
FinalProcessedCollection<Service, Optional<Long>> collection =
        new FilteredCollection<Service>(service -> service.getDataCenter().equals("Chaplin"))
        .compose(new SortedCollection<>(Service::getNodesCount))
        .compose(new LimitedCollection<>(3))
        .compose(new MappedCollection<>(Service::getRequestsPerSecond))
        .compose(new ReducedCollection<>(Long::sum));

collection.renew(fetchServices());
System.out.println(collection.currentState());

/**
Вывод:
    407
*/
```


### TableViewCollection

Сделайте коллекцию `TableViewCollection implements FinalProcessedCollection`, которая будет превращать элементы в **Table** из библиотеки `visualizer`.

Конструктор принимает `String tableName` - название таблицы, `List<ColumnProvider>` - информацию о колонках таблицы.

`ColumnProvider` - nested class `TableViewCollection`. Его **приватный** конструктор принимает `String title` - название колонки и `Function fieldExtractor` - функцию, которая принимает объект коллекции и возвращает его поле. 
Для конструирования `ColumnProvider` следует сделать **публичный** статический метод `of`, который будет вызывать **приватный** конструктор.

##### Пример
```java 
var collection =
    new SortedCollection<>(Service::getRequestsForUptime).compose(
    new TableViewCollection<>("Test", List.of(
            TableViewCollection.ColumnProvider.of("Name", Service::getName),
            TableViewCollection.ColumnProvider.of("Data center", Service::getDataCenter),
            TableViewCollection.ColumnProvider.of("Ping", Service::getAveragePing),
            TableViewCollection.ColumnProvider.of("Available nodes", Service::getNodesCount),
            TableViewCollection.ColumnProvider.of("Requests/sec", Service::getRequestsPerSecond),
            TableViewCollection.ColumnProvider.of("Started time", Service::getStartedTime),
            TableViewCollection.ColumnProvider.of("Current time", Service::getCurrentTime)
        ))
    );

collection.renew(fetchServices());

TerminalRenderer renderer = TerminalRenderer.init(1);
renderer.render(List.of(collection.currentState()));
```

##### Результат

![Zhabdex monitoring](
https://i.ibb.co/ZhXHhHt/zhabdex.gif)

### GroupingCollection

Сделайте коллекцию `GroupingCollection implements ProcessedCollection<T, Map.Entry<? extends K, ? extends List<? extends T>>>`, которая будет группировать элементы по какому-то общему признаку.

Конструктор принимает `Function <? super T, ? extends K> classifier`.

##### Пример
```java 
var collection =
    new GroupingCollection<>(Service::getDataCenter)
        .compose(
            new MappedCollection<>(
                entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().stream().mapToLong(Service::getRequestsPerSecond).sum())
            )
        ).compose(a
            new TableViewCollection<>("Summary ping", List.of(
                TableViewCollection.ColumnProvider.of("Name", Map.Entry::getKey),
                TableViewCollection.ColumnProvider.of("Available nodes", Map.Entry::getValue)
            ))
        );

collection.renew(fetchServices());

TerminalRenderer renderer = TerminalRenderer.init(1);
renderer.render(List.of(collection.currentState()));
```


### Используем библиотеку
Теперь используя написанную библиотеку, сделайте несколько мониторингов. Например, суммарный RPS в каждом из дата-центров и т. д.

### Фреймворк для мониторингов

Сейчас добавлять новые мониторинги не очень удобно. Сделаем небольшой фреймворк, который абстрагирует нас от отображения таблиц.

Код фреймворка должен находится в пакете `monitoring_lib`.

С помощью этого фреймворка для добавления мониторинга достаточно будет сделать класс, который реализует интерфейс `Monitoring` и аннотирован аннотацией `@ActiveMonitoring`.

#### Пример 1

```java
@ActiveMonitoring
public class PingMonitoring implements Monitoring {
    FinalProcessableCollection<Service, Table> collection =
        new SortedCollection<>(Service::getAveragePing).concat(
        new TableViewCollection<>("Ping monitoring", List.of(
                TableViewCollection.ColumnProvider.of("Name", Service::getName),
                TableViewCollection.ColumnProvider.of("Data center", Service::getDataCenter),
                TableViewCollection.ColumnProvider.of("Ping", Service::getAveragePing),
                TableViewCollection.ColumnProvider.of("Requests/sec", Service::getRequestsPerSecond),
                TableViewCollection.ColumnProvider.of("Started time", Service::getStartedTime),
                TableViewCollection.ColumnProvider.of("Current time", Service::getCurrentTime)
          )));

    @Override
    public void update(Collection<? extends Service> services) {
        collection.renew(services);
    }

    @Override
    public Table getStatistics() {
        return collection.currentState();
    }
}
```

Таким образом, фрейворк должен найти в заданном пакете все классы, которые аннотированы аннотацией `@ActiveMonitoring` и реализуют интерфейс `Monitoring`.

Интерфейс `Monitoring` содердит методы `update(), getStatistics()`, сигнатуру которых можно узнать из примера выше.

Запуск приложения, которое написано с помощью фреймворка должен выглядеть следующим образом:

```java
public class Main {
    public static void main(String[] args) throws Exception {
        MonitoringApplication
            .builder()
            .package("by.zhabdex.my_monitoring")
            .serviceURL("http://zhabdex.ovi.by/status")
            .addScanner(new ClassMonitoringScanner())
            .addScanner(new ContainerMonitoringScanner())
            .start();
    }
}
```

* `MonitoringApplication` - основной класс фреймворка, который сканирует все мониторинги в заданном пакете, раз в секунду отправляет запрос к сервису, затем обновляет информацию во мониторингах и с помощью библиотеки отображает таблицы.
* `ClassMonitoringScanner`, `ContainerMonitoringScanner` - классы, которые ищут декларированные мониторинги в пакете, от них ниже.
* `package` - пакет, в котором находятся мониторинги.

Для поиска классов в пакете, которые аннотированы `@ActiveMonitoring` следует воспользоваться библиотекой `Reflections`. Она уже подключена в `build.gradle` модуля `common`. Это значит, что вам достаточно спулить последнюю версию репозитория и уже можно будет использовать у себя в проекте или самостоятельно добавить ее в `build.gradle`. 

Гайд по использованию библиотеки: https://clck.ru/YUYTm

`MonitoringApplication` будет не сам искать мониторинги в пакете, этот код будет находится в реализациях интерфейса `MonitoringScanner`.

```java
@FunctionalInterface
public interface MonitoringScanner {
    Collection<Monitoring> scan(Reflections reflection);
}
```

`ClassMonitoringScanner` - `MonitoringScanner`, который находит в пакете все мониторинги, которые реализуют интерфейс `Monitoring` и аннотированы аннотацией `@ActiveMonitoring`. **(см. Пример 1)**

`ContainerMonitoringScanner` - `MonitoringScanner`, который находит все классы, которые аннотированы аннотацией `@MonitoringContainer`, в нем находит все методы, которые аннотированы аннотацией `@ActiveMonitoring` и возвращают либо `Monitoring`, либо `FinalProcessableCollection<Service, Table>` и в таком случае мониторинг создается используя внутри коллекцию, которая создается в результате вызова метода **(см. Пример 2)**

### Пример 2

```java
@MonitoringContainer
public class Container {
    @ActiveMonitoring
    static FinalProcessableCollection<Service, Table> top2Nodes() {
        return new SortedCollection<>(Service::getNodesCount)
            .concat(new LimitedCollection<>(2))
            .concat(new TableViewCollection<>("top nodes", List.of(
                    TableViewCollection.ColumnProvider.of("Name", Service::getName),
                    TableViewCollection.ColumnProvider.of("Available nodes", Service::getNodesCount)
            )));
    }

    @ActiveMonitoring
    static FinalProcessableCollection<Service, Table> summaryPing() {
        return new AggregatedCollection<>(Service::getDataCenter)
            .concat(
                new MappedCollection<>(
                    entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().stream().mapToLong(Service::getRequestsPerSecond).sum())
                )
            ).concat(
                new TableViewCollection<>("Summary ping", List.of(
                    TableViewCollection.ColumnProvider.of("Name", Map.Entry::getKey),
                    TableViewCollection.ColumnProvider.of("Available nodes", Map.Entry::getValue)
                ))
            );
    }
}
```
