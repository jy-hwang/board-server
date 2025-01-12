package com.fastcampus.boardserver.controller;

import com.fastcampus.boardserver.Service.impl.PostServiceImpl;
import com.fastcampus.boardserver.Service.impl.UserServiceImpl;
import com.fastcampus.boardserver.aop.LoginCheck;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.dto.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {

  private final UserServiceImpl userService;

  private final PostServiceImpl postService;

  public PostController(UserServiceImpl userService, PostServiceImpl postService) {
    this.userService = userService;
    this.postService = postService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountNo, @RequestBody PostDTO postDTO) {
    postService.register(accountNo, postDTO);
    CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPosts", postDTO);

    return ResponseEntity.ok(commonResponse);
  }

  @GetMapping("my-posts")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<CommonResponse<PostDTO>> myPostsInfo(String accountId) {
    UserDTO memberInfo = userService.getUserInfo(accountId);
    List<PostDTO> postDTOList = postService.getMyPosts(memberInfo.getNo());
    CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "myPostsInfo", postDTOList);

    return ResponseEntity.ok(commonResponse);
  }

  @PatchMapping("{postNo}")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<CommonResponse<PostResponse>> updatePosts(
          String accountId, @RequestBody PostRequest postRequest, @PathVariable("postNo") int postNo) {
    UserDTO memberInfo = userService.getUserInfo(accountId);
    PostDTO postDTO
            = PostDTO.builder()
            .no(postNo)
            .title(postRequest.getTitle())
            .contents(postRequest.getContents())
            .categoryNo(postRequest.getCategoryNo())
            .userNo(memberInfo.getNo())
            .updateTime(LocalDateTime.now())
            .build();

    postService.update(postDTO);

    CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePosts", postDTO);

    return ResponseEntity.ok(commonResponse);
  }

  @DeleteMapping("{postNo}")
  @LoginCheck(type = LoginCheck.UserType.USER)
  public ResponseEntity<CommonResponse<PostDeleteRequest>> deletePosts(
          String accountId, @PathVariable("postNo") int postNo
  ) {
    UserDTO memberInfo = userService.getUserInfo(accountId);
    postService.delete(memberInfo.getNo(), postNo);

    PostDeleteRequest postDeleteRequest = new PostDeleteRequest();
    postDeleteRequest.setPostNo(postNo);
    postDeleteRequest.setUserNo(memberInfo.getNo());

    CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deletePosts", postDeleteRequest);

    return ResponseEntity.ok(commonResponse);
  }


  // -- response 객체 --
  @Getter
  @AllArgsConstructor
  private static class PostResponse {
    private List<PostDTO> postDTOs;
  }

  // -- request 객체 --
  @Setter
  @Getter
  private static class PostRequest {
    private String title;
    private String contents;
    private int categoryNo;
  }

  @Setter
  @Getter
  private static class PostDeleteRequest {
    private int userNo;
    private int postNo;

  }
}
