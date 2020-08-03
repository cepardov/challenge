package com.cepardov.challenge.entity;

import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.validator.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
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

    @NotEmpty
    private String name;

    @NotEmpty
    @Password
    private String password;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    private String token;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date modified;

    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    private boolean isActive = true;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Phone> phones = new HashSet<>();

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
        userDTO.setToken(this.getToken());
        userDTO.setCreated(this.getCreated());
        userDTO.setModified(this.getModified());
        userDTO.setLastLogin(this.getLastLogin());
        userDTO.setActive(this.isActive());
        userDTO.setPhones(this.getPhones().stream()
                .map(Phone::toDTO)
                .collect(Collectors.toSet()));
        return userDTO;
    }

}
