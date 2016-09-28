package compare.parsers.csv;

import compare.parsers.csv.parsers.ApacheCommonsCSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class ApacheCommonsCSVTests implements ParserTesting {
    private static Logger LOGGER = LogManager.getLogger();
    private String name;
    private final int iterationCount = 50;

    public ApacheCommonsCSVTests(String name) {
        this.name = name;
    }

    public void apacheParseAll() throws IOException {
        List<Long> times = new LinkedList<>();
        int SIZE_OF_BUFFER = 8192;

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new ApacheCommonsCSVParser(name).parseAll(SIZE_OF_BUFFER));
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }

    @Override
    public void parseLineByLineWithoutBuffer() throws IOException {
        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new ApacheCommonsCSVParser(name).parseLineByLine());
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }

    @Override
    public void parseLineByLineWithBuffer(int bufferSize) throws IOException {
        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= iterationCount) {
            times.add(new ApacheCommonsCSVParser(name).parseLineByLineWithBuffer(bufferSize));
            attempt++;
        }

        LOGGER.info("File: Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }
}
