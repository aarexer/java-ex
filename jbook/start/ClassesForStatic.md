## Введение
Если у нас возникает необходимость написать класс, содержащий только статические методы и переменные, то: подумайте - нужен ли вам такой класс?

Если вы понимаете, что вы не пишите в процедурном стиле на Java, то давайте подумаем:
Зачем он может быть нужен?

С помощью таких классов удобно собирать вместе общие методы работы с данными, например, как сделано в `java.lang.Math`, для методов статической генерации объектов(как в `java.util.Collections`).

Понятно, что раз все в классе у нас статика, то возможность создавать объекты такого класса - не нужна.

## Как работать?
* Невозможность создания объекта класса
* Исключить возможность наследования

По умолчанию, даже если мы этого не пишем, класс имеет конструктор.

Как мы можем запретить делать объекты класса(использование конструктора)?
* Сделать класс abstract-ным
* Вынести все в interface
* Объявить конструктор private

**Не делайте** такой класс abstract-ом. Да, мы не сможем создать объект такого класса, но помните, что `abstract` - он для другого. Он для наследования и не надо сбивать столку этим.

Отличным вариантом будет создать один приватный конструктор. При этом можно еще там выкидывать Exception, чтобы случайно не создать в классе объект.

Также можно все выносить в interface, но тогда никому не мешает этот интерфейс реализовать, что не будет для нас плюсом.

Дабы избежать случайного наследования - хорошо объявить такой класс `final`.


Пример такого класса:

[Example](../../patterns/src/main/java/good/ClassForStatic.java)


#### Когда использовать
Как мы проговорили, очень полезно бывает вынести какие-то внутренние константы в отдельный класс. Как сделать это можно - понятно, но чтобы разграничить области констант, чтобы было понятнее полезно создать еще вложенные статические классы, в которые поместить уже ваши константы.

Вынеся в такие классы данные вы разграничите логику, что всегда хорошо. Т.е например у вас есть константы для User-а и еще для чего-то, можно все запихнуть в один класс и смешать все в кучу, а можно сделать вот так: [Example](../../patterns/src/main/java/good/ClassForConstants.java)

Теперь вы можете обращаться к константе как:
```java
ClassForConstants.User.NAME_LENGTH
```
И это намного понятнее, чем если бы все было в одном классе.