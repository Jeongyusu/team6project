package shop.mtcoding.project.reply;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.community.Community;
import shop.mtcoding.project.user.User;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 댓글작성(Integer sessionId, ReplyRequest.ReplySaveDTO replySaveDTO) {

        // 공백 또는 null 방지
        if (replySaveDTO.getComment() == null || replySaveDTO.getComment().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }
        Reply reply = Reply.builder()
                .comment(replySaveDTO.getComment())
                .user(User.builder().id(sessionId).build())
                .community(Community.builder().id(replySaveDTO.getBoardId()).build())
                .build();

        replyRepository.save(reply);
    }
}
