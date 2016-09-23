package compare.parsers.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.generators.CSVFileGenerator;

import java.io.File;
import java.io.IOException;

public class Examples {
    private static Logger LOGGER = LogManager.getLogger();
    private static String name = "TEST.csv";
    private static int SIZE_OF_BUFFER = 31457280;
    private static Integer[] columns = {10, 50, 100, 300};

    public static void main(String[] args) throws Exception {
        testOpenCSV();
        testSuperCSV();
        testApacheCommonsCSV();
    }

    private static void testApacheCommonsCSV() throws IOException {
        LOGGER.info("-------------------------SUPER CSV---------------------------");
        ApacheCommonsCSVTests test = new ApacheCommonsCSVTests(name);
        for (Integer column : columns) {
            for (int rows = 10; rows < 1_000_000; rows *= 10) {
                createFile(column, rows);
                LOGGER.info("---------------------Line By Line-----------------------------");
                test.apacheCSVTestTimeLineByLineParsing();
                LOGGER.info("---------------------Line By Line With Buffer-----------------");
                test.apacheCSVTestTimeLineByLineWithBufferParsing();
//                LOGGER.info("--------------------All In List------------------------------");
//                test.apacheParseAll();
                LOGGER.info("-----------------------------------------------------------------");
            }
        }
    }

    private static void testSuperCSV() throws IOException {
        LOGGER.info("-------------------------SUPER CSV---------------------------");
        SuperCSVTests test = new SuperCSVTests(name);
        for (Integer column : columns) {
            for (int rows = 10; rows < 1_000_000; rows *= 10) {
                createFile(column, rows);
                LOGGER.info("---------------------Line By Line-----------------------------");
                test.superCSVTestTimeLineByLineParsing();
                LOGGER.info("---------------------Line By Line With Buffer-----------------");
                test.superCSVTestTimeLineByLineWithBufferParsing();
                LOGGER.info("-----------------------------------------------------------------");
            }
        }
    }

    private static void testOpenCSV() throws IOException {
        LOGGER.info("-------------------------OPEN CSV---------------------------");
        OpenCSVTests test = new OpenCSVTests(name);
        for (Integer column : columns) {
            for (int rows = 10; rows < 1_000_000; rows *= 10) {
                createFile(column, rows);
                LOGGER.info("---------------------Line By Line-----------------------------");
                test.openCSVTestTimeLineByLineParsing();
                LOGGER.info("---------------------Line By Line With Buffer-----------------");
                test.openCSVTestTimeLineByLineWithBufferParsing();
//                LOGGER.info("--------------------All In List------------------------------");
//                test.openCSVTestTimeAllInList();
                LOGGER.info("-----------------------------------------------------------------");
            }
        }
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
}
