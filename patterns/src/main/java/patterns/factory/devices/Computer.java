package patterns.factory.devices;

public class Computer {
    private String manufacturer;

    public Computer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
