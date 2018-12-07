package aarexer.spring.example.first;

public class ProflingController implements ProflingControllerMBean {
    private boolean isEnabled = true;

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
