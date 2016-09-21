package utils.generators;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CSVFileGenerator {
    private static Logger LOGGER = LogManager.getLogger();
    private char separator;
    private int lengthOfRecord;
    private CSVFormat csvFileFormat;
    private Random random;

    public CSVFileGenerator(Character separator, int lengthOfRecord) {
        this.separator = separator;
        this.lengthOfRecord = lengthOfRecord;
        this.csvFileFormat = CSVFormat.DEFAULT.withDelimiter(separator);
        random = new Random(separator.hashCode());
    }

    public void generateNewFile(String filename, int numberOfRecords) {

        try (
                FileWriter fw = new FileWriter(filename);
                CSVPrinter csvPrinter = new CSVPrinter(fw, csvFileFormat)
        ) {

            for (int i = 0; i < numberOfRecords; i++) {
                csvPrinter.printRecord(createRecord());
            }

        } catch (IOException e) {
            LOGGER.error("Can't work with file: {}", filename);
        }
    }

    private List<String> createRecord() {

        final List<String> record = new LinkedList<>();

        for (int i = 0; i < lengthOfRecord; i++) {
            record.add(String.valueOf(random.nextInt(1024)));
        }

        return record;
    }

    public void setSeparator(Character separator) {
        this.separator = separator;
    }

    public void setLengthOfRecord(int lengthOfRecord) {
        this.lengthOfRecord = lengthOfRecord;
    }
}
