package compare.parsers.csv.parsers;

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

public class ApacheCommonsCSVParser implements ParseLineByLine {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;

    public ApacheCommonsCSVParser(String filename) {
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private long parse(CSVParser csvParser) {

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

        return System.currentTimeMillis() - start;
    }

    @Override
    public long parseLineByLine() throws IOException {
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withDelimiter(','));
        return parse(csvParser);
    }

    @Override
    public long parseLineByLineWithBuffer(int bufferSize) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader, bufferSize);
        CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(','));

        return parse(csvParser);
    }

    public long parseAll(int bufferSize) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader, bufferSize);

        CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withDelimiter(','));
        long start = System.currentTimeMillis();
        List<CSVRecord> records = csvParser.getRecords();

        bufferedReader.close();
        csvParser.close();

        return System.currentTimeMillis() - start;
    }
}
