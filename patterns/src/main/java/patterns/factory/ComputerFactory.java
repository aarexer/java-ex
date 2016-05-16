package patterns.factory;

import patterns.factory.devices.*;

public class ComputerFactory {
    public Computer getComputer(ComputerType type) {
        Computer computer = null;
        switch (type) {
            case DESKTOP:
                computer = new DesktopComputer("HP");
                break;
            case NOTEBOOK:
                computer = new NotebookComputer("Acer");
                break;
            case TABLET:
                computer = new TabletComputer("ASUS");
                break;
        }

        return computer;
    }
}
