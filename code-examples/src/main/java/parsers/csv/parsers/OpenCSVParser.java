package parsers.csv.parsers;

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
public class OpenCSVParser implements ParseLineByLine {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;

    public OpenCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private long parseFileByLine(CSVReader reader) throws IOException {
        String[] nextLine;

        List<Long> times = new LinkedList<>();
        long start = System.currentTimeMillis();
        long startTmp = System.nanoTime();

        while ((nextLine = reader.readNext()) != null) {
            //do smth with data - filtering and etc
            //some work - for example adding times to list
            times.add(System.nanoTime() - startTmp);
            startTmp = System.nanoTime();
        }

        long stopParsingFileTime = System.currentTimeMillis() - start;

        times.sort(Long::compareTo);
        LOGGER.info("Lines: median: {} nanosecs, average {} nanosecs", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));

        reader.close();

        return stopParsingFileTime;
    }

    @Override
    public long parseLineByLine() throws IOException {
        CSVReader reader = new CSVReader(fileReader, ',', '"', 0);

        return parseFileByLine(reader);
    }

    @Override
    public long parseLineByLineWithBuffer(int bufferSize) throws IOException {
        CSVReader reader = new CSVReader(new BufferedReader(fileReader, bufferSize), ',', '"', 0);

        return parseFileByLine(reader);
    }

    public long parseFileAsList(int bufferSize) throws IOException {
        CSVReader reader = new CSVReader(new BufferedReader(fileReader, bufferSize), ',', '"', 0);

        long start = System.currentTimeMillis();
        List<String[]> records = reader.readAll();

        reader.close();

        return System.currentTimeMillis() - start;
    }
}
