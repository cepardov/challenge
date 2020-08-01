package com.cepardov.chanllenge.dto;

import lombok.Data;

/**
 * @author cepardov on 31-07-20
 */
@Data
public class PhoneDTO {

    private Long id;
    private Integer number;
    private Integer citycode;
    private Integer countrycode;
    private UserDTO userDTO;
}
