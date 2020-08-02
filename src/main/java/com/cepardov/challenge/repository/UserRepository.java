package com.cepardov.challenge.repository;

import com.cepardov.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cepardov on 31-07-20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
