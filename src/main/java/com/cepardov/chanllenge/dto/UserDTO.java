package com.cepardov.chanllenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * @author cepardov on 31-07-20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private boolean isActive;
    private Set<PhoneDTO> phones;
}
