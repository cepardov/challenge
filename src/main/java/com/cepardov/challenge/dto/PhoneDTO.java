package com.cepardov.challenge.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cepardov on 31-07-20
 */
@Getter
@Setter
public class PhoneDTO {

    private Long id;
    private Integer number;
    private Integer citycode;
    private Integer countrycode;
    private UserDTO userDTO;
}
