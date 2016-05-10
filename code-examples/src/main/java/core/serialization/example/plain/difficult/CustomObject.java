package core.serialization.example.plain.difficult;

import java.io.Serializable;

public class CustomObject implements Serializable {
    private transient boolean b;
}
