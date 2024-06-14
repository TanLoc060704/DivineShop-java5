package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import poly.java5divineshop.Divineshop.Data.Entity.RoleE;

@Repository
public interface RoleRepo extends JpaRepository<RoleE, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into Roles (username,username_user,role) " +
            "values (:username, :usernameUser, :role)", nativeQuery = true)
    int saveRole(@Param("username") String username,
                 @Param("usernameUser") String usernameUser,
                 @Param("role") String role);

    @Modifying
    @Transactional
    @Query(value = "delete from Roles where username = :username", nativeQuery = true)
    int deleteRoleByUsername(@Param("username") String username);

    @Query(value = "select * from Roles r where r.username = :username and r.role = :role", nativeQuery = true)
    RoleE getRoleByUsernameAndRole(@Param("username") String username, @Param("role") String role);
}
