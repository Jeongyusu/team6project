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

    @Transactional
    public void 댓글삭제(Integer sessionId, Integer id) {
        Optional<Reply> replyOP = replyRepository.findById(id);
        if (replyOP.isPresent()) {
            Reply reply = replyOP.get();

            // 권한 인증
            if (sessionId != reply.getUser().getId()) {
                throw new MyException("댓글 삭제의 권한이 없습니다.");
            }

            replyRepository.deleteById(id);
        } else {
            throw new MyException("해당 댓글을 찾을 수 없습니다.");
        }
    }
}
