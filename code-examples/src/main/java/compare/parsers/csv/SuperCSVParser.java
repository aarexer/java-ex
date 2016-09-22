package compare.parsers.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class SuperCSVParser {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;
    private static int SIZE_OF_BUFFER = 31457280;

    public SuperCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readWithCsvListReaderWithBuffer() throws Exception {
        LOGGER.info("Creating parser with buffer....");
        ICsvListReader listReader = new CsvListReader(new BufferedReader(fileReader, SIZE_OF_BUFFER), CsvPreference.STANDARD_PREFERENCE);
        parseFileWithCsvList(listReader);

        listReader.close();
    }

    public void readWithCsvListReaderWithoutBuffer() throws Exception {
        LOGGER.info("Creating parser without buffer....");
        ICsvListReader listReader = new CsvListReader(fileReader, CsvPreference.STANDARD_PREFERENCE);
        parseFileWithCsvList(listReader);

        listReader.close();
    }


    private void parseFileWithCsvList(ICsvListReader listReader) throws IOException {
        List<String> customerList;
        List<Long> times = new LinkedList<>();
        long start = System.currentTimeMillis();
        long startTmp = start;

        while ((customerList = listReader.read()) != null) {
            times.add(System.currentTimeMillis() - startTmp);
            startTmp = System.currentTimeMillis();
        }

        LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
        LOGGER.info("Average time of parsing one line: {}", times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }
}
