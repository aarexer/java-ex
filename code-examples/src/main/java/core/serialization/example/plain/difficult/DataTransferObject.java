package core.serialization.example.plain.difficult;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DataTransferObject extends NonSerializable implements Serializable {
    private int i;
    private String data;
    private transient String[] arrOfStr;
    private CustomObject cob;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getMyData());
    }

    private void readObject(ObjectInputStream out) throws IOException, ClassNotFoundException{
        out.defaultReadObject();
        String myData = (String)out.readObject();
        setData(myData);
    }

    //For output
    @Override
    public String toString() {
        return "Int i:" + i +
                ", String data: " + data +
                ", arrOfStr: " + Arrays.toString(arrOfStr) +
                ", CustomObject: " + cob +
                ", myData: " + getMyData();
    }


    public void setI(int i) {
        this.i = i;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setArrOfStr(String[] arrOfStr) {
        this.arrOfStr = arrOfStr;
    }

    public void setCob(CustomObject cob) {
        this.cob = cob;
    }
}
