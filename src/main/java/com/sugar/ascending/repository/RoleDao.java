package com.sugar.ascending.repository;

import com.sugar.ascending.model.Role;

import java.util.List;

public interface RoleDao {
    boolean save(Role role);
    boolean update(Role role);
    boolean delete(String roleName);
    List<Role> getRoles();
    List<Role> getRoleById(int id1,int id2);
    List<Role> getRoleByBusinessId(int bid);
    List<Role> getRoleByCustomerId(int cid);
}
