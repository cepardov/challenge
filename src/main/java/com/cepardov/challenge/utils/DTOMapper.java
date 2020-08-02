package com.cepardov.challenge.utils;

import com.cepardov.challenge.dto.PhoneDTO;
import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.entity.Phone;
import com.cepardov.challenge.entity.User;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author cepardov on 01-08-20
 */
public class DTOMapper {

    private DTOMapper() {
    }

    private static void toEntity(UserDTO userDTO, User user){
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLastLogin(userDTO.getLastLogin());
        user.setActive(userDTO.isActive());
        user.setPhones(
                userDTO.getPhones() == null ? Collections.emptySet():
                        userDTO.getPhones().stream().map(DTOMapper::toEntity).collect(Collectors.toSet())
        );
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        toEntity(userDTO, user);
        return user;
    }

    private static void toEntity(PhoneDTO phoneDTO, Phone phone){
        phone.setId(phoneDTO.getId());
        phone.setNumber(phoneDTO.getNumber());
        phone.setCitycode(phoneDTO.getCitycode());
        phone.setCountrycode(phoneDTO.getCountrycode());
        phone.setUser(phone.getUser());
    }

    public static Phone toEntity(PhoneDTO phoneDTO){
        Phone phone = new Phone();
        toEntity(phoneDTO, phone);
        return phone;
    }

}
