package com.josejavier.repository;

import com.josejavier.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    /**
     *  funcion par optener un cliente por su username o mail
     * @param username
     * @param mail
     * @return Client
     */
    @Query("SELECT c FROM Client c WHERE c.username = :username OR c.mail = :mail")
    Client findByUsernameOrMail(@Param("username") String username, @Param("mail") String mail);


    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Client findbyid(int id);

    /**
     *  funcion par optener un cliente por su username y password
     * @param username
     * @param password
     * @return Client
     */
    @Query("SELECT c FROM Client c WHERE c.username = :username and c.password = :password")
    Client findbyUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
