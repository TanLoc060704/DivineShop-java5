package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import poly.java5divineshop.Divineshop.Data.Entity.UserE;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserE, Integer> {
    @Query(value = "select u from UserE u")
    List<UserE> getAllUser();

    @Query(value = "select u from UserE u where u.tenDangNhap = :tenDangNhap")
    UserE getUserByTenDangNhap(@Param("tenDangNhap") String tenDangNhap);

    @Query(value = "select u from UserE u " +
            "join RoleE r on u.tenDangNhap = r.usernameUser " +
            "where u.tenDangNhap = :tenDangNhap and r.role = :role")
    UserE getUserByUsernameAndRole(@Param("tenDangNhap") String tenDangNhap, @Param("role") String role);

    @Modifying
    @Transactional
    @Query(value = "update [User] set ho_va_ten = :hoVaTen, anh_dai_dien = :anhDaiDien where ten_dang_nhap = :tenDangNhap", nativeQuery = true)
    int updateUserByTenDangNhap(@Param("hoVaTen") String hoVaTen, @Param("anhDaiDien") String anhDaiDien, @Param("tenDangNhap") String tenDangNhap);

    UserE findBytenDangNhap(String tenDangNhap);
}
