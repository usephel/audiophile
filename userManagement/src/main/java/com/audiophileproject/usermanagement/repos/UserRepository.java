package com.audiophileproject.usermanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.audiophileproject.usermanagement.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    public User findByUsername(String username);

}
