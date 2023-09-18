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

    //// 댓글 등록
    @Transactional
    public void 댓글작성(Integer sessionId, ReplyRequest.ReplySaveDTO replySaveDTO) {

        // 공백 또는 null 방지
        if (replySaveDTO.getComment() == null || replySaveDTO.getComment().isEmpty()) {
            throw new MyException("내용을 입력해주세요");
        }

        Reply reply = Reply.builder()
                .comment(replySaveDTO.getComment())
                .user(User.builder().id(sessionId).build())
                .community(Community.builder().id(replySaveDTO.getBoardId()).build())
                .build();

        try {
            replyRepository.save(reply);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

    //// 댓글 삭제
    @Transactional
    public void 댓글삭제(Integer sessionId, Integer replyId) {
        Optional<Reply> replyOP = replyRepository.findById(replyId);
        if (!replyOP.isPresent()) {
            throw new MyException("해당 댓글을 찾을 수 없습니다.");
        }
        Reply reply = replyOP.get();

        // 권한 인증
        if (sessionId != reply.getUser().getId()) {
            throw new MyException("댓글 삭제의 권한이 없습니다.");
        }
        try {
            replyRepository.deleteById(replyId);
        } catch (Exception e) {
            throw new MyException("에러가 발생했습니다. 이유 : " + e.getMessage());
        }

    }

}