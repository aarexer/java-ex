package compare.parsers.csv;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

/**
 * Последняя версия библиотеки датирована июлем 2011 года.
 */
public class OpenCSVParser {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;
    private static int SIZE_OF_BUFFER = 31457280;

    public OpenCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseByLineWithBuffer() throws IOException {
        LOGGER.info("Creating parser with buffer....");
        CSVReader reader = new CSVReader(new BufferedReader(fileReader, SIZE_OF_BUFFER), ',', '"', 0);

        parseFileByLine(reader);

        reader.close();
    }

    public void parseByLineWithoutBuffer() throws IOException {
        LOGGER.info("Creating parser without buffer....");
        CSVReader reader = new CSVReader(fileReader, ',', '"', 0);

        parseFileByLine(reader);

        reader.close();
    }

    private void parseFileByLine(CSVReader reader) throws IOException {
        String[] nextLine;
        LOGGER.info("Start parsing....");

        List<Long> times = new LinkedList<>();
        long start = System.currentTimeMillis();
        long startTmp = start;

        while ((nextLine = reader.readNext()) != null) {
            //do smth with data - filtering and etc
            times.add(System.currentTimeMillis() - startTmp);
            startTmp = System.currentTimeMillis();
        }
        LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
        LOGGER.info("Average time of parsing one line: {}", times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }

    public void parseFileAsList() throws IOException {
        LOGGER.info("Creating parser as list parsing....");
        CSVReader reader = new CSVReader(new BufferedReader(fileReader), ',', '"', 0);

        long start = System.currentTimeMillis();
        List<String[]> records = reader.readAll();

        LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
        reader.close();
    }
}
