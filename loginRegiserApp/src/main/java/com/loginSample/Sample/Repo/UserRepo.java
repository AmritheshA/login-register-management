package com.loginSample.Sample.Repo;

import com.loginSample.Sample.Entity.Entitys;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Entitys, Integer> {
    Entitys findByName(String name);
    boolean existsByName(String name);

}
