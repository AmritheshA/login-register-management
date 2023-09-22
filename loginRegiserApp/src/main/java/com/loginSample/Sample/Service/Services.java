package com.loginSample.Sample.Service;

import com.loginSample.Sample.Entity.Entitys;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface Services {

    Entitys findByName(String userName);

    Entitys save(Entitys userDetails);

    List<Entitys> findAll();

    boolean nameIsExist(String name);

    Optional<Entitys> findById(int id);

    public void deleteById(Integer id);

    void deleteAllUsers();
}
