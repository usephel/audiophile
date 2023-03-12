package com.audiophileproject.usermanagement.repos;


import com.audiophileproject.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public abstract Optional<User> findByEmail(String email);
    public abstract Optional<User> findByUsername(String username);
    public abstract void deleteByUsername(String username);

}
