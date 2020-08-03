package com.cepardov.challenge.service;

import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author cepardov on 01-08-20
 */
public interface UserService {

    List<UserDTO> findAll();
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    UserDTO findById(Long id);
    void delete(UserDTO userDTO);
    void deleteById(Long id);
    UserDTO findByEmail(String email);
    Optional<User> findByToken(String token);
    Map<String, Object> getDetails(String email);
}
