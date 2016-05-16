package patterns.factory;

import patterns.factory.devices.ComputerType;

public class Example {
    public static void main(String[] args) {
        ComputerFactory factory = new ComputerFactory();
        System.out.println(factory.getComputer(ComputerType.DESKTOP));
    }
}
