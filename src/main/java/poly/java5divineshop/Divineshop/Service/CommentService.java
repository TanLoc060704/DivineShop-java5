package poly.java5divineshop.Divineshop.Service;


import poly.java5divineshop.Divineshop.Data.Dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDTO> getAllComments();

    Optional<CommentDTO> getCommentById(Integer id);

    CommentDTO createComment(CommentDTO commentDTO);

    Optional<CommentDTO> updateComment(Integer id, CommentDTO commentDTO);

    boolean deleteComment(Integer id);

    List<CommentDTO> getCommentByProduct_Slug(String slug);
}
