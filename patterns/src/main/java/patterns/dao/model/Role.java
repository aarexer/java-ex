package patterns.dao.model;

/**
 * Roles for Users
 * Simple Role class for Users in example
 */
public class Role extends Model {
    private String title;
    private String description;

    public Role(Long id, String title, String description) {
        super(id);
        this.title = title;
        this.description = description;
    }

    public Role(Long id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
