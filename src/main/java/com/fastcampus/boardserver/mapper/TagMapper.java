package com.fastcampus.boardserver.mapper;

import com.fastcampus.boardserver.dto.TagDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

  int register(TagDTO tagDTO);

  void update(TagDTO tagDTO);

  void delete(int tagNo);

  void createPostTag(int tagNo, int postNo);
}
