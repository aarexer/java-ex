### Введение
Строки. По подсчетам, около 25% объектов в Java приложениях - это строки. И понятно почему - программы пишутся в основном для людей, а мы привыкли иметь дело именно со строками.

Поэтому понимать то, с чем мы имеем дело - необходимо.

#### Что за строки в Java?
Главное, что стоит всегда помнить про строки в Java - они иммутабельны, т.е неизменяемые.

Внутри `java.lang.String` у нас не так много полей, а именно:
```java
 /** The value is used for character storage. */
    private final char value[];

    /** Cache the hash code for the string */
    private int hash; // Default to 0
```

Остальные два поля я не написал, они относятся к теме сериализации.

Соответственно, понятно, что значение строки у нас в массиве value[].

При этом, если посмотреть, то в String занимает довольно много места. Почему? Тут все зависит от представления объектов в JVM, о наличии обязательного заголовка у объекта, выравнивания на 8 и т.д. Об этом поговорим потом.
Если посмотреть по дампу, то overhead может составить от 12 байт!

При этом использовать String надо!
Поэтому вот и создают в C-подобных языках свои собственные классы строк.
В java мы тоже можем написать свой аналог строки. Но не стоит этого делать обычно, ибо можем наступить на грабли в производительности.

Теперь вернемся к имутабельности.
#### Неизменяемость строк
Из-за того, что спецификация требует, чтобы после операции `+` создавалась новая строка, то писать что-то подобное:
```java
String s = "Hello";
for(int i = 0; i < 1000; i++)
   s += "World";
```
Очень плохо.
Ибо мы создадим огромное количество бесполезных объектов.

Выход есть через использование `StringBuilder`:
```java
StringBuilder sb = new StringBuilder();
for(int i = 0; i < 1000; i++)
   s.append("World");

sb.toString();
```

При этом есть еще `StringBuffer`, отличается он тем, что он thread-safe, а значит он медленнее, чем билдер.
В основном, лучше использовать `StringBuilder`.

И этот способ **НАМНОГО** быстрее обычной конкатенации.
А все потому, что иммутабельность - это всегда дополнительные расходы.

#### Concat под капотом
Когда мы пишем что-то типа:
```java
"s1" + "s2"
```
То этот плюс преобразуется в байткоде в созданный объект `StringBuilder-а`, а после произойдет append. При этом эти StringBuilder-ы неплохо оптимизируется JVM(эту оптитмизацию можно выключить специальным ключом).
И именно из-за этой оптимизации будет быстрее работать:
```java
public String test() { return "" + 14; }
```
Чем:
```java
public String test() { return String.valueOf(14); }
public String test2() { return Integer.toString(14); }
```
Но только без сайд-эффектов, с сайд-эффектами уже не сработает быстро:
```java
private int x = 14;
public String test() { return "" + (x++); } //side-effect ++
```

Но опять же, это все прекрасно но стоит ли это более понятного человеку `String.valueOf()` -вопрос спорный, в 99% случаев нет, лучше писать более понятно человеку, чем выигрывать немного в скорости..
#### Equals
//todo

#### Intern
//todo

#### HashCode
```java
    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
```

Т.е зависит линейно от размера строки.
При этом есть поле, кеширующее хешкод.
```java
    /** Cache the hash code for the string */
    private int hash; // Default to 0
```