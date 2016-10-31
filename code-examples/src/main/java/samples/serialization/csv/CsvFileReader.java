package samples.serialization.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

    public static void readCsvFile(String filename) {
        List<Student> students = new ArrayList<>();

        CSVParser csvParser = null;

        try (FileReader fileReader = new FileReader(filename)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter('|');
            csvParser = new CSVParser(fileReader, csvFormat);

            List<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord record : records) {
                students.add(new Student(Long.valueOf(record.get(0)), record.get(1), record.get(2), Integer.valueOf(record.get(3))));
            }

            for (Student student : students)
                System.out.println(student);

            writeCsvToFile("students_Ser.csv", students);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvParser != null)
            {
                try {
                    csvParser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeCsvToFile(String filename, List<Student> list) {
        CSVPrinter csvFilePrinter = null;
        try (FileWriter fileWriter = new FileWriter(filename)) {
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            for (Student student : list){
                List<String> studetnDataRecord = new ArrayList<>();

                studetnDataRecord.add(String.valueOf(student.getId()));
                studetnDataRecord.add(student.getName());
                studetnDataRecord.add(student.getSurname());
                studetnDataRecord.add(String.valueOf(student.getAge()));

                csvFilePrinter.printRecord(studetnDataRecord);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvFilePrinter != null) {
                try {
                    csvFilePrinter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeCsvToFileAsRecord(String filename) {
        CSVPrinter csvFilePrinter = null;
        try (FileWriter fileWriter = new FileWriter(filename)) {
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            csvFilePrinter.printRecord(14, "Name_Of", "Surname_Of", 25);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
