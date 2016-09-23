package compare.parsers.csv;

import compare.parsers.csv.parsers.SuperCSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class SuperCSVTests {
    private static Logger LOGGER = LogManager.getLogger();
    private String name;
    private final int iterationCount = 50;

    public SuperCSVTests(String name) {
        this.name = name;
    }

    public void superCSVTestTimeLineByLineParsing() throws IOException {

        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new SuperCSVParser(name).parseLineByLine());
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }

    public void superCSVTestTimeLineByLineWithBufferParsing() throws IOException {
        List<Long> times = new LinkedList<>();
        int SIZE_OF_BUFFER = 8192;

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new SuperCSVParser(name).parseLineByLineWithBuffer(SIZE_OF_BUFFER));
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }
}
