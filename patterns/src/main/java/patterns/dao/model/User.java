package patterns.dao.model;

import java.util.List;

/**
 * It's a simple class for dao example
 * Simple User class which contains information about user:
 * firstname, secondname, login, password and age.
 */
public class User extends Model {

    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private int age;

    private List<Role> role;
    private List<Group> groups;

    public User(Long id) {
        super(id);
    }

    public User(Long id, String firstName, String secondName, String login, String password, int age) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public User(Long id, String firstName, String secondName, String login, String password, int age, List<Role> role, List<Group> groups) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.age = age;
        this.role = role;
        this.groups = groups;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
