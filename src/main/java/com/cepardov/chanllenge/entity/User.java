package com.cepardov.chanllenge.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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


}
