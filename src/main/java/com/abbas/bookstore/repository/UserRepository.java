package com.abbas.bookstore.repository;
import com.abbas.bookstore.model.Role;
import com.abbas.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail (String email);

    @Modifying
    @Query("update User set role = :role where email = :email")
    void updateUserRole(@Param("email") String email, @Param("role") Role role);
}
