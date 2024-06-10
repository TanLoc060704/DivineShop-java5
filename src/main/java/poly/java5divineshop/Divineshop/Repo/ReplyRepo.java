package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;

import java.util.Date;
import java.util.List;

@Repository
public interface ReplyRepo extends JpaRepository<ReplyE,Integer> {
    List<ReplyE> findAll();
    ReplyE save(ReplyE replyE);
    @Modifying
    @Query(value = "INSERT INTO Reply (noi_dung, ngay_tra_loi, Sys_id_user, Sys_id_comment) VALUES (:noiDung, :ngayTraLoi, :userId, :commentId)", nativeQuery = true)
    void saveReply(@Param("noiDung") String noiDung, @Param("ngayTraLoi") Date ngayTraLoi, @Param("userId") Integer userId, @Param("commentId") Integer commentId);
}
