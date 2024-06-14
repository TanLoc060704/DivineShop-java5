package poly.java5divineshop.Divineshop.Service;

import poly.java5divineshop.Divineshop.Data.Dto.ReplyDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    List<ReplyDTO> getAllReplys();

    Optional<ReplyDTO> getReplyById(Integer id);

    ReplyDTO createReply(ReplyDTO replyDTO);

    Optional<ReplyDTO> updateReply(Integer id, ReplyDTO replyDTO);

    boolean deleteReply(Integer id);
}
