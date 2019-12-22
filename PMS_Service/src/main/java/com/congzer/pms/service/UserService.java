package com.congzer.pms.service;

import com.congzer.pms.domain.Role;
import com.congzer.pms.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws  Exception;

    UserInfo findById(String id) throws  Exception;

    List<Role> findRolesByUserId(String id) throws Exception;

    void saveRole(String userId, String roleId) throws Exception;
}
