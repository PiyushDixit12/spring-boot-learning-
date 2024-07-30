package com.datamodeling.DataModeling.repositories;
import com.datamodeling.DataModeling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

