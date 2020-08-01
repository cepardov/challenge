package com.cepardov.chanllenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author cepardov on 31-07-20
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer number;
    private Integer citycode;
    private Integer countrycode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
