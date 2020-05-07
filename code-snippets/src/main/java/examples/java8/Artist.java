package examples.java8;

import lombok.Data;

import java.util.List;

@Data
public class Artist {
    private String name;
    private List<String> albums;

    public Artist(String name) {
        this.name = name;
    }
}
