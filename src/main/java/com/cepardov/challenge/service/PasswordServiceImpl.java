package com.cepardov.challenge.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * @author cepardov on 02-08-20
 */
@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
