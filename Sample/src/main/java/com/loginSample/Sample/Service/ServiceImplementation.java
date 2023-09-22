package com.loginSample.Sample.Service;

import com.loginSample.Sample.Entity.Entitys;
import com.loginSample.Sample.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplementation implements Services{



    @Autowired
    private UserRepo curdRepositry;

    public ServiceImplementation(UserRepo curdRepositry) {
        this.curdRepositry = curdRepositry;
    }


    @Override
    public Entitys findByName(String userName) {
        return curdRepositry.findByName(userName);
    }

    @Override
    public Entitys save(Entitys userDetails) {

        return curdRepositry.save(userDetails);
    }

    @Override
    public List<Entitys> findAll() {
        return curdRepositry.findAll();
    }

    @Override
    public boolean nameIsExist(String name) {
        return curdRepositry.existsByName(name);
    }

    @Override
    public Optional<Entitys> findById(int id) {
        return curdRepositry.findById(id);

    }
    @Override
    public void deleteById(Integer id) {
        curdRepositry.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        curdRepositry
                .findAll()
                .stream()
                .filter((user) ->user.getRole().equals("USER"))
                .forEach(curdRepositry::delete);
    }
}
