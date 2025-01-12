package com.fastcampus.boardserver.Service.impl;

import com.fastcampus.boardserver.Service.PostSearchService;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.request.PostSearchRequest;
import com.fastcampus.boardserver.mapper.PostSearchMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PostSearchServiceImpl implements PostSearchService {

  @Autowired
  private PostSearchMapper postSearchMapper;

  @Cacheable(value = "getPosts", key = "'getPosts'+#postSearchRequest.getTitle()+#postSearchRequest.getCategoryNo()")
  @Override
  public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
    List<PostDTO> postDTOList = null;

    try {
      postDTOList = postSearchMapper.selectPosts(postSearchRequest);
    } catch (RuntimeException e) {
      log.error("selectPosts 메서드 실패", e.getMessage());
    }
    return postDTOList;
  }

}
