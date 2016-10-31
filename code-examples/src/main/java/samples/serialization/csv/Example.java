package samples.serialization.csv;

public class Example {
    public static void main(String[] args) {
        CsvFileReader.readCsvFile(Example.class.getResource("/students.csv").getFile());
    }
}
