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

    @Modifying
    @Query("update User set email = :email where id = :id")
    void updateUserEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query("update User set firstname = :firstname where id = :id")
    void updateUserFirstname(@Param("id") Long id, @Param("firstname") String firstname);

    @Modifying
    @Query("update User set lastname = :lastname where id = :id")
    void updateUserLastname(@Param("id") Long id, @Param("lastname") String lastname);
    Optional<User> findById(Long id);
}
