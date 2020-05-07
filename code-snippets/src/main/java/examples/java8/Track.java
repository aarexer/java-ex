package examples.java8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Track {
    private String name;
    private int duration;
}
