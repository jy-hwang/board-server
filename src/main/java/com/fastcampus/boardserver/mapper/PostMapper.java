package com.fastcampus.boardserver.mapper;

import com.fastcampus.boardserver.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

  int register(PostDTO postDTO);

  List<PostDTO> selectMyPosts(int accountNo);

  void update(PostDTO postDTO);

  void delete(PostDTO postDTO);
}
