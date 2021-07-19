package com.example.aop.mapStruct.exercise.repository;
import com.example.aop.mapStruct.exercise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String Id);

}
