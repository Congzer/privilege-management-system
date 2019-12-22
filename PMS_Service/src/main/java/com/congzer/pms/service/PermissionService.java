package com.congzer.pms.service;

import com.congzer.pms.domain.Permission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PermissionService {
    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
