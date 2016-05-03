package patterns.dao.impl;

import patterns.dao.GroupDao;
import patterns.dao.model.Group;

import java.util.List;

public class GroupDatabaseDao implements GroupDao {


    @Override
    public Group getBytitle(String title) {
        return null;
    }

    @Override
    public Group getByUrl(String url) {
        return null;
    }

    @Override
    public Group getById(Long id) {
        return null;
    }

    @Override
    public List<Group> getAll() {
        return null;
    }

    @Override
    public void put(Group item) {

    }

    @Override
    public boolean update(Group item) {
        return false;
    }

    @Override
    public boolean delete(Group item) {
        return false;
    }
}
