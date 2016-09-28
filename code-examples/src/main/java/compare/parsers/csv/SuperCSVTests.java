package compare.parsers.csv;

import compare.parsers.csv.parsers.SuperCSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class SuperCSVTests implements ParserTesting {
    private static Logger LOGGER = LogManager.getLogger();
    private String name;
    private final int iterationCount = 50;

    public SuperCSVTests(String name) {
        this.name = name;
    }

    @Override
    public void parseLineByLineWithoutBuffer() throws IOException {
        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new SuperCSVParser(name).parseLineByLine());
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }

    @Override
    public void parseLineByLineWithBuffer(int bufferSize) throws IOException {
        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new SuperCSVParser(name).parseLineByLineWithBuffer(bufferSize));
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }
}
