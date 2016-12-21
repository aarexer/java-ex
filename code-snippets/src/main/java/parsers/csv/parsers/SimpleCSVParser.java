package parsers.csv.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Double.NaN;

public class SimpleCSVParser {
    private static Logger LOGGER = LogManager.getLogger();
    private FileReader fileReader;
    private static int SIZE_OF_BUFFER = 31457280;

    public SimpleCSVParser(String filename) {
        try {
            this.fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parseWithBuffer() throws IOException {
        LOGGER.info("Starting parsing with buffer");
        BufferedReader br = new BufferedReader(fileReader);
        String line;

        List<Long> times = new LinkedList<>();
        long start = System.currentTimeMillis();
        long startTmp = start;

        while ((line = br.readLine()) != null) {
            String[] country = line.split(",");
            times.add(System.currentTimeMillis() - startTmp);
            startTmp = System.currentTimeMillis();
        }

        LOGGER.info("File parsed by: {}", System.currentTimeMillis() - start);
        LOGGER.info("Average time of parsing one line: {}", times.stream().mapToLong(Long::longValue).average().orElse(NaN));

        br.close();
    }
}