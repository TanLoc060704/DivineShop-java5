package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.CommentDTO;
import poly.java5divineshop.Divineshop.Data.Dto.ReplyDTO;
import poly.java5divineshop.Divineshop.Data.Entity.CategoryE;
import poly.java5divineshop.Divineshop.Data.Entity.CommentE;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;
import poly.java5divineshop.Divineshop.Data.Model.CommentM;
import poly.java5divineshop.Divineshop.Data.Model.ReplyM;
import poly.java5divineshop.Divineshop.Repo.CommentRepo;
import poly.java5divineshop.Divineshop.Repo.ReplyRepo;
import poly.java5divineshop.Divineshop.Service.CommentService;
import poly.java5divineshop.Divineshop.Service.ReplyService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ReplyService replyService;

    @Override
    public List<CommentDTO> getAllComments() {
        List<CommentE> comments = commentRepo.findAll();
        return comments.stream()
                .map(CommentM::convertCommentEToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentDTO> getCommentById(Integer id) {
        Optional<CommentE> commentEOptional = commentRepo.findById(id);
        return commentEOptional.map(CommentM::convertCommentEToCommentDTO);
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        CommentE commentE = CommentM.convertCommentDTOToCommentE(commentDTO);
        CommentE savedCommentE = commentRepo.save(commentE);
        return CommentM.convertCommentEToCommentDTO(savedCommentE);
    }

    @Override
    public Optional<CommentDTO> updateComment(Integer id, CommentDTO commentDTO) {
        CommentE commentE = CommentM.convertCommentDTOToCommentE(commentDTO);
        commentE.setReplies(null);
        Optional<CommentE> tempCME = commentRepo.findById(id);

        for (ReplyE replyE : commentDTO.getReplies()) {
            Optional<ReplyDTO> tempReply = replyService.getReplyById(replyE.getSysIdReply());

            if (tempReply.isPresent()) {
                commentE.addReplyList(ReplyM.convertReplyDTOToReplyE(tempReply.get()));
            }else {
                ReplyE tempE = new ReplyE(replyE.getNoiDung(), replyE.getNgayTraLoi(), replyE.getUser(), tempCME.get());
                ReplyE tempRep = ReplyM.convertReplyDTOToReplyE(replyService.createReply(ReplyM.convertReplyEToReplyDTO(tempE)));
                commentE.addReplyList(tempRep);
            }
        }
        return commentRepo.findById(id)
                .map(temp ->{
                    temp.setNoiDung(commentDTO.getNoiDung());
                    temp.setReplies(commentE.getReplies());
                    commentRepo.save(temp);
                    return CommentM.convertCommentEToCommentDTO(temp);
                });
    }

    @Override
    public boolean deleteComment(Integer id) {
        return false;
    }
}
