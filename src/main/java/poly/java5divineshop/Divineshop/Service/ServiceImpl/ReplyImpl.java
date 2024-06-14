package poly.java5divineshop.Divineshop.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.java5divineshop.Divineshop.Data.Dto.ReplyDTO;
import poly.java5divineshop.Divineshop.Data.Entity.ReplyE;
import poly.java5divineshop.Divineshop.Data.Model.ReplyM;
import poly.java5divineshop.Divineshop.Repo.ReplyRepo;
import poly.java5divineshop.Divineshop.Service.ReplyService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReplyImpl implements ReplyService {

    @Autowired
    ReplyRepo replyRepository;

    @Override
    public List<ReplyDTO> getAllReplys() {
        List<ReplyE> replyEList = replyRepository.findAll();
        return replyEList.stream()
                .map(ReplyM::convertReplyEToReplyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReplyDTO> getReplyById(Integer id) {
        if(id == null){
            return Optional.empty();
        }else{
            Optional<ReplyE> replyEOptional = replyRepository.findById(id);
            return replyEOptional.map(ReplyM::convertReplyEToReplyDTO);
        }
    }

    @Override
    public ReplyDTO createReply(ReplyDTO replyDTO) {
        ReplyE replyE = ReplyM.convertReplyDTOToReplyE(replyDTO);
        ReplyE savedReplyE = replyRepository.save(replyE);
        return ReplyM.convertReplyEToReplyDTO(savedReplyE);
    }

    @Override
    public Optional<ReplyDTO> updateReply(Integer id, ReplyDTO replyDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteReply(Integer id) {
        return false;
    }
}

