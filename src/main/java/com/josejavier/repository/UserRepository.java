package com.josejavier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josejavier.model.user;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<user, Integer> {

}
