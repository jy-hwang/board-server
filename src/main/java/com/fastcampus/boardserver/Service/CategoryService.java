package com.fastcampus.boardserver.Service;

import com.fastcampus.boardserver.dto.CategoryDTO;

public interface CategoryService {

  void register(String userId, CategoryDTO categoryDTO);

  void update(CategoryDTO categoryDTO);

  void delete(int categoryNo);

}
