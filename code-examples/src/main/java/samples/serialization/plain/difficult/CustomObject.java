package samples.serialization.plain.difficult;

import java.io.Serializable;

public class CustomObject implements Serializable {
    private transient boolean b;
}
