package shop.mtcoding.project.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import shop.mtcoding.project._core.error.ex.MyException;
import shop.mtcoding.project._core.util.FormatDate;
import shop.mtcoding.project.community.CommunityResponse.BoardDetailDTO;
import shop.mtcoding.project.community.CommunityResponse.BoardListDTO;
import shop.mtcoding.project.reply.Reply;
import shop.mtcoding.project.reply.ReplyRepository;
import shop.mtcoding.project.reply.ReplyResponse.ReplyDetailDTO;
import shop.mtcoding.project.user.User;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ReplyRepository replyRepository;

    //// 커뮤니티 게시물 리스트
    public Page<BoardListDTO> 게시물목록(Integer page) {
        // 페이징
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Community> communityPage = communityRepository.findAll(pageable);

        List<BoardListDTO> boardListDTOList = new ArrayList<>();
        for (Community community : communityPage) {
            // board 날짜 포맷 -> 예) 2022-11-12
            Date boardCreatedAt = community.getCreatedAt();
            String boardFormatDate = FormatDate.formatDate(boardCreatedAt);

            // board 정보 DTO
            BoardListDTO dtos = BoardListDTO.builder()
                    .boardId(community.getId())
                    .title(community.getTitle())
                    .content(community.getContent())
                    .boardUserName(community.getUser().getUserName())
                    .boardFormatDate(boardFormatDate)
                    .build();

            boardListDTOList.add(dtos);
        }

        return new PageImpl<>(boardListDTOList, pageable, communityPage.getTotalElements());
    }

    //// 검색 + 커뮤니티 게시물 리스트
    public Page<BoardListDTO> 검색후게시물목록(Integer page, String keyword) {
        Pageable pageable = PageRequest.of(page, 3, Sort.Direction.DESC, "id");
        Page<Community> communityPG = communityRepository.mfindBySearchAll(pageable, keyword);

        List<BoardListDTO> boardListDTOList = new ArrayList<>();
        for (Community community : communityPG.getContent()) {

            // board 날짜 포맷 -> 예) 2022-11-15
            Date boardCreatedAt = community.getCreatedAt();
            String boardFormatDate = FormatDate.formatDate(boardCreatedAt);

            // board 정보 DTO
            BoardListDTO dtos = BoardListDTO.builder()
                    .boardId(community.getId())
                    .title(community.getTitle())
                    .content(community.getContent())
                    .boardUserName(community.getUser().getUserName())
                    .boardFormatDate(boardFormatDate)
                    .build();

            boardListDTOList.add(dtos);
        }

        return new PageImpl<>(boardListDTOList, pageable, communityPG.getTotalElements());
    }

    //// 게시물 등록
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

    //// 게시물 상세
    public BoardDetailDTO 상세게시물(Integer sessionId, Integer id) {

        Optional<Community> communityOP = communityRepository.mfindByIdJoinReplyAndBoard(id);

        if (communityOP.isPresent()) {
            Community community = communityOP.get();

            // baord 권한 인증
            boolean boardOwner = false;
            if (sessionId != null) {
                boardOwner = sessionId == community.getUser().getId();
            }

            // board 날짜 포맷 -> 예) 2022-11-15
            Date boardCreatedAt = community.getCreatedAt();
            String boardFormatDate = FormatDate.formatDate(boardCreatedAt);

            List<Reply> replyList = community.getReplyList();
            List<ReplyDetailDTO> replyDetailDTOList = new ArrayList<>();
            for (Reply reply : replyList) {

                // reply 권한 인증
                boolean replyOwner = false;
                if (sessionId != null) {
                    replyOwner = sessionId == reply.getUser().getId();
                }

                // reply 날짜 포맷 -> 예) 2022-11-15
                Date replyCreatedAt = reply.getCreatedAt();
                String replyFormatDate = FormatDate.formatDate(replyCreatedAt);

                // 댓글 정보 DTO
                ReplyDetailDTO replyDetailDTO = ReplyDetailDTO.builder()
                        .replyId(reply.getId())
                        .comment(reply.getComment())
                        .replyUserName(reply.getUser().getUserName())
                        .replyFormatDate(replyFormatDate)
                        .replyOwner(replyOwner)
                        .build();

                replyDetailDTOList.add(replyDetailDTO);
            }

            // board 정보 DTO(+ reply)
            BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder()
                    .boardId(community.getId())
                    .title(community.getTitle())
                    .content(community.getContent())
                    .boardUserName(community.getUser().getUserName())
                    .boardFormatDate(boardFormatDate)
                    .replyList(replyDetailDTOList)
                    .boardOwner(boardOwner)
                    .build();

            return boardDetailDTO;

        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    //// 게시물 수정 전(원본)
    public Community 게시물내용(Integer sessionId, Integer id) {
        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {
            Community community = communityOP.get();
            // 권한 인증
            if (sessionId != community.getUser().getId()) {
                throw new MyException("게시물 수정의 권한이 없습니다.");
            }
            return communityOP.get();
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    //// 게시물 수정
    @Transactional
    public void 게시물수정(Integer ssessionId, Integer id, CommunityRequest.BoardUpdateDTO boardUpdateDTO) {
        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {
            Community community = communityOP.get();

            // 권한 인증
            if (ssessionId != community.getUser().getId()) {
                throw new MyException("게시물 수정의 권한이 없습니다.");
            }

            // 공백 또는 null 방지
            if (boardUpdateDTO.getTitle() == null || boardUpdateDTO.getTitle().isEmpty()) {
                throw new MyException("내용을 전부 입력해주세요");
            }
            if (boardUpdateDTO.getContent() == null || boardUpdateDTO.getContent().isEmpty()) {
                throw new MyException("내용을 전부 입력해주세요");
            }

            // update
            community.setTitle(boardUpdateDTO.getTitle());
            community.setContent(boardUpdateDTO.getContent());
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void 게시물삭제(Integer sessionId, Integer id) {
        Optional<Community> communityOP = communityRepository.findById(id);
        if (communityOP.isPresent()) {
            Community community = communityOP.get();

            // 권한 인증
            if (sessionId != community.getUser().getId()) {
                throw new MyException("게시물 삭제의 권한이 없습니다.");
            }
            communityRepository.deleteById(id);
        } else {
            throw new MyException("해당 게시글을 찾을 수 없습니다.");
        }
    }

}
