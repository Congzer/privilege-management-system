package com.congzer.pms.dao;


import com.congzer.pms.domain.Permission;
import com.congzer.pms.domain.Role;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface RoleDao {

    //协助查询出带有role信息的UserInfo对象
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.congzer.pms.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    /**
     * 根据userId查询出该user不具有的角色信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findRolesByUserId(String id) throws Exception;

    @Select("select * from role where id = #{id}")
    Role findById(String id) throws Exception;

    /**
     * 根据roleId查询出该角色所不拥有的权限
     * @param id
     * @return
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findPermissionsByRoleId(@Param("roleId") String id);

    /**
     * 给角色添加权限
     * @param permissionId
     * @param roleId
     * @throws Exception
     */
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermission(@Param("permissionId") String permissionId,@Param("roleId") String roleId) throws Exception;
}
