package poly.java5divineshop.Divineshop.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<CommentE,Integer> {
    List<CommentE> findAll();

    @Query(value = "SELECT * from Comment", nativeQuery = true)
    List<CommentE> findAllComment();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Comment SET replies = :replies WHERE Sys_id_comment = :commentId", nativeQuery = true)
    void updateReplies(@Param("commentId") int commentId, @Param("replies") ReplyE repliesJson);

    CommentE save(CommentE commentE);
}
