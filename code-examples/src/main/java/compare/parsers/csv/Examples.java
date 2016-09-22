package compare.parsers.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.generators.CSVFileGenerator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class Examples {
    private static Logger LOGGER = LogManager.getLogger();
    private static String name = "TEST.csv";
    private static int SIZE_OF_BUFFER = 31457280;

    public static void main(String[] args) throws Exception {
        openCSVTestTimeLineByLineParsing(10, 1000);
    }

    private static void createFile(int columns, int rows) {
        CSVFileGenerator csvFileGenerator = new CSVFileGenerator(',', columns);
        csvFileGenerator.generateNewFile(name, rows);

        double sizeInBytes = new File(name).length();

        LOGGER.info("Generate file with {} columns, {} rows.", columns, rows);

        if ((int) sizeInBytes / (1024 * 1024) > 0) {
            LOGGER.info("File size is {} mb", sizeInBytes / (1024 * 1024));
        } else if ((int) sizeInBytes / 1024 > 0) {
            LOGGER.info("File size is {} kb", sizeInBytes / 1024);
        } else {
            LOGGER.info("File size is {} bytes", sizeInBytes);
        }
    }

    private static void openCSVTestTimeLineByLineParsing(int columns, int rows) throws IOException {
        createFile(columns, rows);
        List<Long> times = new LinkedList<>();

        long attempt = 0;
        while (attempt <= 200_000) {
            times.add(new OpenCSVParser(name).parseLineByLine());
            attempt++;
        }

        LOGGER.info("Median: {} mills, average: {} mills", times.get(times.size() / 2), times.stream().mapToLong(Long::longValue).average().orElse(NaN));
    }
}
