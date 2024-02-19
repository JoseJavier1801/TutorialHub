package com.josejavier.repository;

import com.josejavier.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c.id AS user_id, c.photo AS user_photo, c.name AS user_name, c.username AS user_username, " +
            "c.mail AS user_mail, c.password AS user_password, c.date AS user_date, " +
            "c.phone AS user_phone FROM Client c WHERE c.username = :username")
    Client findByUsername(String username);

    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Client findbyid(int id);

    @Query("SELECT c FROM Client c WHERE c.username = :username and c.password = :password")
    Client findbyUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
