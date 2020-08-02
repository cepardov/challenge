package com.cepardov.challenge.service;

import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.entity.User;
import com.cepardov.challenge.repository.UserRepository;
import com.cepardov.challenge.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cepardov on 01-08-20
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = DTOMapper.toEntity(userDTO);
        return userRepository.save(user).toDTO();
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return user.toDTO();
    }

    @Override
    public void delete(UserDTO userDTO) {
        userRepository.delete(DTOMapper.toEntity(userDTO));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id);
    }
}
