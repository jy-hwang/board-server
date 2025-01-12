package com.fastcampus.boardserver.Service.impl;

import com.fastcampus.boardserver.Service.PostService;
import com.fastcampus.boardserver.dto.CommentDTO;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.TagDTO;
import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.mapper.CommentMapper;
import com.fastcampus.boardserver.mapper.PostMapper;
import com.fastcampus.boardserver.mapper.TagMapper;
import com.fastcampus.boardserver.mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService {

  @Autowired
  private PostMapper postMapper;

  @Autowired
  private UserProfileMapper userProfileMapper;

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private TagMapper tagMapper;

  @Override
  public void register(String userId, PostDTO postDTO) {
    UserDTO memberInfo = userProfileMapper.getUserProfile(userId);
    postDTO.setUserNo(memberInfo.getNo());
    postDTO.setCreateTime(LocalDateTime.now());

    if (memberInfo != null) {
      postMapper.register(postDTO);
      Integer postNo = postDTO.getNo();

      for (int i = 0; i < postDTO.getTagDTOList().size(); i++) {
        TagDTO tagDTO = postDTO.getTagDTOList().get(i);
        tagMapper.register(tagDTO);
        Integer tagNo = tagDTO.getNo();
        tagMapper.createPostTag(tagNo, postNo);
      }

    } else {
      log.error("register Post Error! {}", postDTO);
      throw new RuntimeException("register Post Error! 게시글 등록 메서드를 확인해주세요!" + postDTO);
    }

  }

  @Override
  public List<PostDTO> getMyPosts(int accountNo) {
    List<PostDTO> postDtoList = postMapper.selectMyPosts(accountNo);

    return postDtoList;
  }

  @Override
  public void update(PostDTO postDTO) {
    if (postDTO != null && postDTO.getDeleteTime() == null) {
      postMapper.update(postDTO);
    } else {
      log.error("update Post Error! {}", postDTO);
      throw new RuntimeException("update Post Error! 게시글 수정 메서드를 확인해주세요!" + postDTO);
    }
  }

  @Override
  public void delete(int userNo, int postNo) {
    if (userNo != 0 && postNo != 0) {
      PostDTO postDTO
              = PostDTO.builder()
              .no(postNo)
              .userNo(userNo)
              .deleteTime(LocalDateTime.now())
              .build();

      postMapper.delete(postDTO);
    } else {
      log.error("delete Post Error! {}", postNo);
      throw new RuntimeException("delete Post Error! 게시글 삭제 메서드를 확인해주세요!" + postNo);
    }
  }

  @Override
  public void registerComment(CommentDTO commentDTO) {
    if (commentDTO.getPostNo() != 0) {
      commentMapper.register(commentDTO);
    } else {
      log.error("register Comment Error! {}", commentDTO);
      throw new RuntimeException("register Comment Error! 댓글 등록 메서드를 확인해주세요!" + commentDTO);
    }
  }

  @Override
  public void updateComment(CommentDTO commentDTO) {
    if (commentDTO != null) {
      commentMapper.update(commentDTO);
    } else {
      log.error("update Comment Error! {}", commentDTO);
      throw new RuntimeException("update Comment Error! 댓글 수정 메서드를 확인해주세요!" + commentDTO);
    }
  }

  @Override
  public void deleteComment(int userNo, int commentNo) {
    if (userNo != 0 && commentNo != 0) {
      CommentDTO commentDTO
              = CommentDTO.builder()
              .no(commentNo)
              .deleteTime(LocalDateTime.now())
              .build();
      commentMapper.delete(commentDTO);
    } else {
      log.error("delete Comment Error! {}", commentNo);
      throw new RuntimeException("delete Comment Error! 댓글 삭제 메서드를 확인해주세요!" + commentNo);
    }
  }

  @Override
  public void registerTag(TagDTO tagDTO) {
    if (tagDTO != null) {
      tagMapper.register(tagDTO);
    } else {
      log.error("register Tag Error! {}", tagDTO);
      throw new RuntimeException("register Tag Error! Tag 등록 메서드를 확인해주세요!" + tagDTO);
    }
  }

  @Override
  public void updateTag(TagDTO tagDTO) {
    if (tagDTO != null) {
      tagMapper.update(tagDTO);
    } else {
      log.error("update Tag Error! {}", tagDTO);
      throw new RuntimeException("update Tag Error! Tag 수정 메서드를 확인해주세요!" + tagDTO);
    }
  }

  @Override
  public void deleteTag(int userNo, int tagNo) {
    if (userNo != 0 && tagNo != 0) {
      tagMapper.delete(tagNo);
    } else {
      log.error("delete Tag Error!");
      throw new RuntimeException("delete Tag Error! Tag 삭제 메서드를 확인해주세요!");
    }
  }
}
