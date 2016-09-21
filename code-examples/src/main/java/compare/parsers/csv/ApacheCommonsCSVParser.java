package compare.parsers.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class ApacheCommonsCSVParser {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;
    private static int SIZE_OF_BUFFER = 31457280;

    public ApacheCommonsCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseFileWithBufferAndRecords() {
        try (
                BufferedReader bufferedReader = new BufferedReader(fileReader, SIZE_OF_BUFFER);
                CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(','))
        ) {
            LOGGER.info("Start parsing with buffer....");
            long start = System.currentTimeMillis();
            List<CSVRecord> records = csvParser.getRecords();
            LOGGER.info("File parsed by {}", System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFileWithIterator() {
        try (
                BufferedReader bufferedReader = new BufferedReader(fileReader, SIZE_OF_BUFFER);
                CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(','))
        ) {
            LOGGER.info("Start parsing....");

            List<Long> times = new LinkedList<>();
            long start = System.currentTimeMillis();
            long startTmp = start;

            Iterator<CSVRecord> iterator = csvParser.iterator();

            CSVRecord record;
            while (iterator.hasNext()) {
                record = iterator.next();
                //do smth with data - filtering and etc
                times.add(System.currentTimeMillis() - startTmp);
                startTmp = System.currentTimeMillis();
            }
            LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
            LOGGER.info("Average time of parsing one line: {}", times.stream().mapToLong(Long::longValue).average().orElse(NaN));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
