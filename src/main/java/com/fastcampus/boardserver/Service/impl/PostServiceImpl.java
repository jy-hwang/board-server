package com.fastcampus.boardserver.Service.impl;

import com.fastcampus.boardserver.Service.PostService;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.mapper.PostMapper;
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

  @Override
  public void register(String userId, PostDTO postDTO) {
    UserDTO memberInfo = userProfileMapper.getUserProfile(userId);
    postDTO.setUserNo(memberInfo.getNo());
    postDTO.setCreateTime(LocalDateTime.now());

    if (memberInfo != null) {
      postMapper.register(postDTO);
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
}
