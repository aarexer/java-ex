package patterns.dao.model;

import java.util.Date;

/**
 * Group class for Users.
 */
public class Group extends Model {
    private String title;
    private String description;
    private String url;
    private Date createDate;

    public Group(Long id) {
        super(id);
    }

    public Group(Long id, String title, String description, String url, Date createDate) {
        super(id);
        this.title = title;
        this.description = description;
        this.url = url;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
