package compare.parsers.csv;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SuperCSVParser implements ParseLineByLine {
    private FileReader fileReader;

    public SuperCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private long parseFileWithCsvList(ICsvListReader listReader) throws IOException {
        List<String> customerList;
        List<Long> times = new LinkedList<>();
        long start = System.currentTimeMillis();
        long startTmp = start;

        while ((customerList = listReader.read()) != null) {
            times.add(System.currentTimeMillis() - startTmp);
            startTmp = System.currentTimeMillis();
        }

        listReader.close();

        return System.currentTimeMillis() - start;
    }

    @Override
    public long parseLineByLine() throws IOException {
        ICsvListReader listReader = new CsvListReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
        return parseFileWithCsvList(listReader);
    }

    @Override
    public long parseLineByLineWithBuffer(int bufferSize) throws IOException {
        ICsvListReader listReader = new CsvListReader(new BufferedReader(fileReader, bufferSize), CsvPreference.STANDARD_PREFERENCE);
        return parseFileWithCsvList(listReader);
    }
}
