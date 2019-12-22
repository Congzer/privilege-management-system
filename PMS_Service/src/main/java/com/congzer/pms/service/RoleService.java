package com.congzer.pms.service;

import com.congzer.pms.domain.Permission;
import com.congzer.pms.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws  Exception;

    Role findById(String id) throws Exception;

    List<Permission> findPermissionsByRoleId(String id) throws Exception;

    void addPermission(String permissionId, String roleId) throws Exception;
}
