package com.cepardov.chanllenge.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

/**
 * @author cepardov on 31-07-20
 */
@Data
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
