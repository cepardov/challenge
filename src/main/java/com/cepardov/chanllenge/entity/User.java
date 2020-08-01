package com.cepardov.chanllenge.entity;

import com.cepardov.chanllenge.dto.PhoneDTO;
import com.cepardov.chanllenge.dto.UserDTO;
import com.cepardov.chanllenge.utils.DTOMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cepardov on 31-07-20
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String password;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date modified;

    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    private boolean isActive = true;

    @OneToMany(mappedBy = "user")
    private Set<Phone> phones;

    @PrePersist
    private void prePersist(){
        this.created = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        this.modified = new Date();
    }

    public UserDTO toDTO() {
        UserDTO userDTO =  new UserDTO();
        userDTO.setId(this.getId());
        userDTO.setName(this.getName());
        userDTO.setPassword(this.getPassword());
        userDTO.setEmail(this.getEmail());
        userDTO.setCreated(this.getCreated());
        userDTO.setModified(this.getModified());
        userDTO.setLastLogin(this.getLastLogin());
        userDTO.setActive(this.isActive());
        userDTO.setPhones(this.getPhones().stream().map(Phone::toDTO).collect(Collectors.toSet()));
        return userDTO;
    }

}
