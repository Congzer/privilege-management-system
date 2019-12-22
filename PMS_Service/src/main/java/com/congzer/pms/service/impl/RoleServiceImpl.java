package com.congzer.pms.service.impl;

import com.congzer.pms.dao.RoleDao;
import com.congzer.pms.domain.Permission;
import com.congzer.pms.domain.Role;
import com.congzer.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findPermissionsByRoleId(String id) throws Exception {
        return roleDao.findPermissionsByRoleId(id);
    }

    @Override
    public void addPermission(String permissionId, String roleId) throws Exception {
        roleDao.addPermission(permissionId,roleId);
    }
}
