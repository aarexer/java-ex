package compare.parsers.csv.parsers;

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

public class SuperCSVParser implements ParseLineByLine {
    private static Logger LOGGER = LogManager.getLogger();
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

        long stopParsingFileTime = System.currentTimeMillis() - start;

        times.sort(Long::compareTo);
        LOGGER.info("Lines: median: {}, average {}", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));

        return stopParsingFileTime;
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
