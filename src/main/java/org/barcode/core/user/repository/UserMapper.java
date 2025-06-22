package org.barcode.core.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.barcode.core.user.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    // 기본적인 CURD부터..
    // 단일 책임 원칙. DB 연산 1회
    // 명명 규칙 어떻게..? 동사+목적어

    // Create
    void insertUser(User user);

    // Read
    User findUserById(Integer userId);
    User findUserByEmail(String email);
    List<User> findAll();

    // Update
    void updateUser(User user);

    // Delete
    void deleteUserById(long userId);

    // 확장 메서드

    // 역할 기반 조회
    List<User> findUserByRole(String role);

    // 이메일 기반 중복 체크. 없으면 0 있으면 1.
    int countByUserEmail(String email);

}
