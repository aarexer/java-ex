##CSV Serialization
Наряду с записью в xml, json и прочее можно записывать еще в csv-файлы.
Это простейший способ. По сути csv-файл - это файл, где значения разделены запятой(но не обязательно запятой, подойдет любой разделитель, например, `|`).
Иногда в начале такого файла идет так называемый Header,где описываются поля.
Пример такого файла:
```java
Id, name, surname, age
1, Aleksandr, Kuchuk, 25
```

Несмотря на то, что кажется устаревшим такой подход он до сих пор применяется. Например для логов платежей.
Для чтения и запись в такие файлы есть несколько библиотек, такие как opencv или apache commons csv.
Приведу пример работы с последней:

Класс, объекты которого будем сериализовать: [Student](../../code-examples/src/main/java/core/serialization/example/csv/Student.java)
Пример парсинга и записи: [Example](../../code-examples/src/main/java/core/serialization/example/csv/CsvFileReader.java)
Запуск(обратите внимание на то, как мы достаем ресурс): [Start](../../code-examples/src/main/java/core/serialization/example/csv/Example.java)
