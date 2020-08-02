package com.cepardov.challenge.service;

import com.cepardov.challenge.dto.UserDTO;

import java.util.List;

/**
 * @author cepardov on 01-08-20
 */
public interface UserService {

    List<UserDTO> findAll();
    UserDTO save(UserDTO userDTO);
    UserDTO findById(Long id);
    void delete(UserDTO userDTO);
    void deleteById(Long id);
}
