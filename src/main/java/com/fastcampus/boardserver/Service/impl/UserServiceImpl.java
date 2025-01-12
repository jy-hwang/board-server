package com.fastcampus.boardserver.Service.impl;

import com.fastcampus.boardserver.Service.UserService;
import com.fastcampus.boardserver.dto.UserDTO;
import com.fastcampus.boardserver.exception.DuplicateIdException;
import com.fastcampus.boardserver.mapper.UserProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.fastcampus.boardserver.util.SHA256Util.encryptSHA256;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

  @Autowired
  private UserProfileMapper userProfileMapper;

  @Override
  public UserDTO getUserInfo(String userId) {
    return userProfileMapper.getUserProfile(userId);
  }

  @Override
  public void register(UserDTO userProfile) {
    boolean dupIdResult = isDuplicate(userProfile.getUserId());
    if (dupIdResult) {
      throw new DuplicateIdException("중복된 아이디입니다.");
    }
    userProfile.setCreateTime(LocalDateTime.now());
    userProfile.setPassword(encryptSHA256(userProfile.getPassword()));
    userProfile.setStatus(userProfile.isAdmin() ? UserDTO.Status.ADMIN : UserDTO.Status.DEFAULT);

    int insertCount = userProfileMapper.insertUserProfile(userProfile);

    if (insertCount != 1) {
      log.error("insertMember Error! {}", userProfile);
      throw new RuntimeException(
              "insertUser ERROR! 회원가입 메서드를 확인하세요\n" + "Params : " + userProfile
      );
    }

  }

  @Override
  public UserDTO login(String id, String password) {
    String cryptPassword = encryptSHA256(password);
    UserDTO memberInfo = userProfileMapper.findByUserIdAndPassword(id, cryptPassword);

    return memberInfo;
  }

  @Override
  public boolean isDuplicate(String id) {
    return userProfileMapper.idCheck(id) == 1;
  }

  @Override
  public void updatePassword(String id, String beforePassword, String afterPassword) {
    String cryptPassword = encryptSHA256(beforePassword);
    UserDTO oldUser = userProfileMapper.findByUserIdAndPassword(id, cryptPassword);

    if (oldUser != null) {
      oldUser.setPassword(encryptSHA256(afterPassword));
      oldUser.setUpdateTime(LocalDateTime.now());
      int updateCount = userProfileMapper.updatePassword(oldUser);
    } else {
      log.error("updatePassword Error!");
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

  }

  @Override
  public void deleteId(String id, String password) {
    String cryptPassword = encryptSHA256(password);
    UserDTO oldUser = userProfileMapper.findByUserIdAndPassword(id, cryptPassword);
    if (oldUser != null) {
      int deleteCount = userProfileMapper.deleteUserProfile(id);
    } else {
      log.error("deleteId Error!");
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

  }

}
