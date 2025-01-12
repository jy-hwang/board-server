package com.fastcampus.boardserver.mapper;

import com.fastcampus.boardserver.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

  int register(CategoryDTO categoryDTO);

  void update(CategoryDTO categoryDTO);

  void delete(int categoryNo);
}
