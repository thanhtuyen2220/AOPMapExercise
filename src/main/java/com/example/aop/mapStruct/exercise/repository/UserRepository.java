package com.example.aop.mapStruct.exercise.repository;
import com.example.aop.mapStruct.exercise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findUserById(String Id);
    User findUserByEmail(String email);

}
