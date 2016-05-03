package patterns.dao.impl;

import patterns.dao.RoleDao;
import patterns.dao.model.Role;

import java.util.List;

public class RoleDatabaseDao implements RoleDao {


    @Override
    public Role getByTitle(String title) {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public void put(Role item) {

    }

    @Override
    public boolean update(Role item) {
        return false;
    }

    @Override
    public boolean delete(Role item) {
        return false;
    }
}
