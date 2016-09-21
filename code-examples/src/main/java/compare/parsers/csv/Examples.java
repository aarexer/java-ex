package compare.parsers.csv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.generators.CSVFileGenerator;

import java.io.File;
import java.io.IOException;

public class Examples {
    private static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        String name = "TEST.csv";
        int numberOfLines = 100_000;
        CSVFileGenerator csvFileGenerator = new CSVFileGenerator(',', 100);
        csvFileGenerator.generateNewFile(name, numberOfLines);

        LOGGER.info("Generate {} file with {} columns and it size: {} mb.", name, numberOfLines, new File(name).length() / 1024);

        System.out.println("---------------------------");
        new OpenCSVParser(name).parseByLineWithoutBuffer();
        System.out.println("---------------------------");
        new OpenCSVParser(name).parseByLineWithBuffer();
        System.out.println("---------------------------");
        new OpenCSVParser(name).parseFileAsList();

        System.out.println("---------------------------");
        System.out.println("-----------Apache----------");
        new ApacheCommonsCSVParser(name).parseFileWithBufferAndRecords();
        System.out.println("---------------------------");
        new ApacheCommonsCSVParser(name).parseFileWithIterator();
    }
}
