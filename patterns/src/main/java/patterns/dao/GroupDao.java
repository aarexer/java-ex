package patterns.dao;

import patterns.dao.model.Group;

public interface GroupDao extends ItemDao<Group> {

    Group getBytitle(String title);

    Group getByUrl(String url);
}
