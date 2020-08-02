package com.cepardov.challenge.service;

/**
 * @author cepardov on 02-08-20
 */
public interface PasswordService {

    String hash(String password);
    boolean verifyHash(String password, String hash);
}
