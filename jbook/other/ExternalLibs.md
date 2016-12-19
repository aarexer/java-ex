## Сторонние библиотеки и их использование
### apache commons-io
Подключение, через maven:
```xml
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.5</version>
</dependency>
```
* Работа с файлами
  Упрощенная работа с файлами, как то - запись и считывание.
  
  Пример записи строки в файл:
  
  ```java
  FileUtils.writeStringToFile(File file, String str, boolean append)
  ```
