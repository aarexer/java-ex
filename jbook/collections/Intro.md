## Введение
Зачем вообще ввели коллекции? Ответ ясен - чтобы увеличение размера массивов и структур данных были из коробки, плюс всякие плюшки для работы с той или иной структурой данных.

Как это все сделано в Java?

Если посмотреть на иерархию, то получится что-то типа:
![](../../images/collections.png)

Видим, что выше всего стоит именно Iterable у коллекций.
Iterable предоставляет нам итератор, для обхода коллекции.

Для начала надо посмотреть на этот самый Iterable.
## Iterable interface

Дает возможность нам получить итератор для текущей коллекции.
В самом же итераторе - три метода: hasNext(), next(), remove().
Итератор необходим для обхода коллекции, удаления значений.

//todo
## Collection interface
Что в нем:

* `add()`
* `clear()`
* `iterator()`
* `size`
* `remove()`
* `isEmpty()`
* `contains()`

### List interface
List расширяет Collection, добавляя туда новые методы. Почему их разнесли?Потому что List - это упорядоченная структура данных, в отличии, от, например, множества.
Поэтому List является отдельным интерфейсом.
Основные вещи, которые List добавляет это:
* get(index)
* set(index, Object)
* indexOf(Object)

Т.е он позволяет нам получить что-то из коллекции или узнать индекс.
Разумеется List параметризован, но я не стал усложнять, про параметризацию - отдельно.

### Реализации List
* ArrayList, на основе массива
* LinkedList, на основе двусвязного списка
* Vector, на основе массива, синхронизована, почти не используется
* Stack, расширение Vector, лучше вместо нее использовать Dequeue

//todo Set, Queue