package utils.hash;

import com.google.common.hash.Hashing;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Hasher {
    public static List<String> calculate(String path) throws IOException {
        List<String> hashes = new ArrayList<>();

        CSVParser parser = CSVParser.parse(new FileReader(path), CSVFormat.EXCEL.withDelimiter(';'));
        parser.getRecords().forEach(record -> {
            for (String value : record) {
                String sha256hex = Hashing.sha512()
                        .hashString(value, StandardCharsets.UTF_8)
                        .toString();

                hashes.add(sha256hex);
            }
        });

        return hashes;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calculate("2.csv"));
    }
}
