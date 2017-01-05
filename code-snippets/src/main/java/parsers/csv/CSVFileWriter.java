package parsers.csv;

import parsers.csv.model.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    private final BufferedWriter writer;
    private final String delimiter;

    public CSVFileWriter(File file, String delimiter) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(file));
        this.delimiter = delimiter;
    }

    public void writeStudent(Student student) throws IOException {
        final StringBuilder s = new StringBuilder();

        s.append(student.getId());
        s.append(",");
        s.append(student.getName());
        s.append(",");
        s.append(student.getSurname());
        s.append(",");
        s.append(student.getAge());

        final String line = s.toString();
        writer.write(line);
    }

    public void close() throws IOException {
        writer.close();
    }
}
