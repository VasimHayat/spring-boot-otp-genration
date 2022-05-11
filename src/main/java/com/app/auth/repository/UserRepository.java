package com.app.auth.repository;

import com.app.auth.beans.EOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<EOUser, Long>  {


//    @Query("SELECT u FROM User u WHERE  u.email = :email")
//    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM EOUser u WHERE u.email = :email")
    List<EOUser> findByEmail(@Param("email") String email);

}
