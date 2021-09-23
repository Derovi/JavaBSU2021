# Дженерики. Никогда не было и вот опять

Вашу компанию наняло правительство крупного мегаполиса Zhabinka, чтобы вы написали софт, который поможет при управлении городом, но... вы очень любите писать мощный API и дженерики. Что было дальше...

## Базовая архитектура в стиле постмодернизма

![alt text](https://lh3.googleusercontent.com/proxy/vC3M0-8702AXvOzQtlFF30RFnWHLHNR1C0x-_mp2NCS9trjNK1HB3fGPmO0a81ARl6GuppqCRoYcFAu2hD8n2oC2W0llLFRmhZJTV5whrltHhg)

Мегаполис состоит из районов (`District`). Каждый район может состоять только из зданий (`Building`) одного типа. Например, район `OfficeDistrict` может состоять только из офисов (`Office extends Building`), т. e. там могут быть `RubinPlaza extends Office` или `ZurichGoogle extends Office`, но не может быть `House extends Building`.

🛠 Реализуем эти классы, а так же несколько конкретных зданий:
```java
abstract class Building {
    float area;
    boolean taxesPaid;
}

class District<...> {
    String address;
    List<Building> buildings;
}

class Office extends Building {
    int coffeeMachines;
    int employees;
}

class Shop extends Building {
    int dailyCustomers;
}

class House extends Building {
    int flatCount;
}
```

##  Район строят строители

![alt text](https://lh3.googleusercontent.com/proxy/M9KDJFMWhsRWnurSDSyiK7O2_cQ-LWWeVqz4q1CSM0tPgYdXnVsMo7DJGI6RGXHdTnsZJsXhbqpw2_7f4qBPHOinHO_DFHdRafRtJTE1BU8bZCkeCmCXh8eOd-Q1Z-8kszU4GJxoIbs)

🛠 Сейчас наш `District` создавать не удобно, давайте реализуем в нем патерн _Builder_, чтобы можно было делать использовать такой интерфейс:

```java
new District<Office>()
  .withAddress("Apple dr, Cupertino, Ca")
  .withBuilding(new ApplePark())
```

## А могут ли камни платить налоги?

Добавим в `District` метод `collectInfo`, который будет собирать какую-то информацию о районе. Он будет принимать какую-то функцию `calculator (building -> optional)`, которая принимает `Building` и возвращает какой-то `Optional<T>`. Метод `collectInfo` будет возвращать `List<T>` - результаты удачного выполнения `calculator` для всех зданий. [Удачное выполнение `calculator` - `Optional.isPresent()`]

🛠 Будем рассчитывать штраф для всех зданий в районе, которые не заплатили налог.

```java
// calculate penalty if building didn't pay taxes
Optional<Penalty> calculatePenalty(Building building) { /* ... */ }
// run function for each building and collect non-empty results
District.collect(/* ... */)
```

🛠 Сделаем метод, который найдет медианное значение штрафа всех зданий в районе
```java
// median fine for city
Option<Float> calculateMedianFine(District<...> district) { /* ... */ }
```

🛠 Настало время считать налог!

```java
List<Penalty> applePenalties = appleOffices.collect(calculatePenalty);
Penalty median = calculateMedianFine(applePenalties);
```

## Субподрядчики

![alt text](https://i.ytimg.com/vi/EDT3-uK1ntA/mqdefault.jpg)

Оказывается, у нас есть много разных видов районов и строителей, которые могут строить только обычные районы уже не хватает...

```java
class OfficeDistrict extends District<...> {
  String company;
}

class ShopDistrict extends District<...> {
  String shop;
}
```

Хочется иметь возможность строить так:

```java
new OfficeDistict()
  .withAddress("Mountain View, CA")
  .withCompany("Google")
  .withBuilding(new GooglePlex())
```

🛠 Ну добавим методы в `OfficeDistrict`, Проблем же не будет?

## 📰 В зоопарке Атланты пытаются излечить от коронавируса 13 горилл 

![alt text](https://cdn.iz.ru/sites/default/files/styles/900x506/public/news-2021-09/20210812_zia_c181_089.jpeg.jpg?itok=uTgM0Tx6)

Некоторые здания предоставляют информацию об случаях заражения короновирусом. Нужно уметь получать эту информацию об районах с такими зданиями.

```java
interface COVIDStatisticsProvider {
    int getCOVIDCases();
}
```

Пусть теперь `Office implements COVIDStatisticsProvider` 

🛠 Сделаем метод `getSummaryCOVIDCases(District<...>)`, который будет принимать только районы, в которых все здания предоставляют короновирусную статистику.

## Научимся собирать аггрегированную статистику

##### Задачи: 
* Узнать сколько жильцов в районе с домами
* Узнать ожидаемую стоимость потребленной воды в офисном районе

Вообще, эти задачи состоят из двух этапов:
* Получить информацию с одного здания - это будет некоторый `extractor`
* Отдать эту информацию в какой-то аггрегатор, а в более общем случае - `consumer`

🛠 Сделаем метод `processStatistics(extractor, consumer)`. Он будет принимать функцию, которая будет получать информацию из здания и функцию, которая будет получать эту информацию.

🛠 Теперь сделаем функцию `extractHouseResidentCount(House house)` и какой-то `SummaryCalculator`.

```java
class SummaryCalculator {
    SummaryCalculator( /* ... */ );
    void add(float value) { /* ... */ }
    float getSum() { /* ... */ };
}
```

🛠 Используем
```java
District<House> houseDistrict = new District<>();
SummaryAggregator aggregator = new SummaryAggregator();
houseDistrict.processStatistics(processStatistics, aggregator::add);
```

🛠 Теперь сделаем то же самое для второй задачи. Метод `predictWaterConsumption(Office office)` будет возвращать количество литров воды, которое ожидаемо потребит офис, а `WaterIncomeCalculator` будет считать сколько ЖКХ получи денег за воду в районе.

```java
class WaterIncomeCalculator {
  WaterIncomeCalculator(float costOfLiter) { /* ... */ }
  addSource(float value) { /* ... */ }
  float getSummaryIncome() { /* ... */ }
}
```

```java
District<Office> officeDistrict = new District<>();
WaterIncomeCalculator calculator = new WaterIncomeCalculator();
officeDistrict.apply(predictWaterConsumption, calculator::addSource);
float result = waterCalculator.getSummaryIncome();
```

## Мегаполисами не рождаются
![alt text](https://www.sb.by/upload/resize_cache/slam.image/iblock/4e0/855_2000_1/4e0ea4d8fa8ebe95545bf6f115a22d55.jpg)

Иногда районы нужно объединять. Но ведь если у нас есть два района с разными типами зданий, то какой тип должен получиться у района, который объединяет эти два? Вообще, иерархия наследования зданий в районе - дерево и наша задача - найти их LCA (наименьшего общего предка).

```java
mergeDistricts(d1, d2) - merge any two districts into one and preserve the LCA
// (District<Office>, District<Shop>) -> District<Building>
// (District<Office>, District<Office>) -> District<Office>

mergeDistricts(d1, d2, supplier) - as above, but with a supplier that creates district inheritor
// (District<Office>, OfficeDisrict, OfficeDistrict::new) -> OfficeDistrict
```

🛠 Кажется, задаа сложная, давайте решать. 
