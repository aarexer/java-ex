package utils.marshaller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class Node<T> implements Serializable {
    private final String name;
    private final T value;
    //todo TypeToken?
    private final String type;

    public Node(String name, T value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
}