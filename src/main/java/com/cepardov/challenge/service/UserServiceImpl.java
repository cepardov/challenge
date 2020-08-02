package com.cepardov.challenge.service;

import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.entity.User;
import com.cepardov.challenge.repository.UserRepository;
import com.cepardov.challenge.utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * @author cepardov on 01-08-20
 */
@Service

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordService passwordService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        userDTO.setPassword(passwordService.hash(userDTO.getPassword()));
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

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(new User());
        return user.toDTO();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = DTOMapper.toEntity(findByEmail(email));
        if (user.getEmail() == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), emptyList()
        );
    }
}
