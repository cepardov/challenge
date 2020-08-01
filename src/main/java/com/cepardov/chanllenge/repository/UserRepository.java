package com.cepardov.chanllenge.repository;

import com.cepardov.chanllenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cepardov on 31-07-20
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
