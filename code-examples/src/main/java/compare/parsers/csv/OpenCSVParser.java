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
        long startTmp = start;

        while ((nextLine = reader.readNext()) != null) {
            //do smth with data - filtering and etc
            //some work - for example adding times to list
            times.add(System.currentTimeMillis() - startTmp);
            startTmp = System.currentTimeMillis();
        }

        reader.close();
        return System.currentTimeMillis() - start;
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

    public void parseFileAsList() throws IOException {
        CSVReader reader = new CSVReader(new BufferedReader(fileReader), ',', '"', 0);

        long start = System.currentTimeMillis();
        List<String[]> records = reader.readAll();

        LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
        reader.close();
    }
}
