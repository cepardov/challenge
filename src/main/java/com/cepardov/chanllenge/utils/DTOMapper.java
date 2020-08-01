package com.cepardov.chanllenge.utils;

import com.cepardov.chanllenge.dto.UserDTO;
import com.cepardov.chanllenge.entity.User;

/**
 * @author cepardov on 01-08-20
 */
public class DTOMapper {

    public DTOMapper() {
    }

    private static void toEntity(UserDTO userDTO, User user){
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLastLogin(userDTO.getLastLogin());
        user.setActive(userDTO.isActive());
        user.setPhones(null);
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        toEntity(userDTO, user);
        return user;
    }
}
