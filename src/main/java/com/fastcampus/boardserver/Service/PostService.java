package com.fastcampus.boardserver.Service;

import com.fastcampus.boardserver.dto.PostDTO;

import java.util.List;

public interface PostService {

  void register(String userId, PostDTO postDTO);

  List<PostDTO> getMyPosts(int accountNo);

  void update(PostDTO postDTO);

  void delete(int userNo, int postNo);


}
