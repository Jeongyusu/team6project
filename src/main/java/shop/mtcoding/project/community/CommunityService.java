package shop.mtcoding.project.community;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project.user.User;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Transactional
    public void 게시물작성(Integer sessionId, CommunityRequest.BoardSaveDTO boardSaveDTO) {

        // 공백 또는 null 방지
        if (boardSaveDTO.getTitle() == null || boardSaveDTO.getTitle().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }
        if (boardSaveDTO.getContent() == null || boardSaveDTO.getContent().isEmpty()) {
            throw new MyException("내용을 전부 입력해주세요");
        }

        Community community = Community.builder()
                .title(boardSaveDTO.getTitle())
                .content(boardSaveDTO.getContent())
                .user(User.builder().id(sessionId).build())
                .build();
        communityRepository.save(community);
    }

    public Community 상세게시물(Integer id) {
        Optional<Community> communityOP = communityRepository.mfindByIdJoinReplyAndBoard(id);
        if (communityOP.isPresent()) {
            return communityOP.get();
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

}
