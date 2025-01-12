package com.fastcampus.boardserver.Service;

import com.fastcampus.boardserver.dto.CommentDTO;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.TagDTO;

import java.util.List;

public interface PostService {

  void register(String userId, PostDTO postDTO);

  List<PostDTO> getMyPosts(int accountNo);

  void update(PostDTO postDTO);

  void delete(int userNo, int postNo);

  void registerComment(CommentDTO commentDTO);

  void updateComment(CommentDTO commentDTO);

  void deleteComment(int userNo, int commentNo);

  void registerTag(TagDTO tagDTO);

  void updateTag(TagDTO tagDTO);

  void deleteTag(int userNo, int tagNo);

}
