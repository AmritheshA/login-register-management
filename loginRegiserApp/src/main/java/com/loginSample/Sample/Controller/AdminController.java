package com.loginSample.Sample.Controller;


import com.loginSample.Sample.Entity.Entitys;
import com.loginSample.Sample.Service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AdminController {


    @Autowired
    private Services services;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/adminPage")
    public String adminPage() {
        return "admin/adminPage";
    }

    @GetMapping("adminPage/search")
    public String search(@RequestParam String name, Model model) {

        Entitys user = services.findByName(name);
        if (user == null) {
            return "redirect:/adminPage";
        }
        model.addAttribute("user", user);
        return "admin/searchPage";
    }

    @GetMapping("adminPage/showAll")
    public String showAll(Model model) {

        List<Entitys> allDetails = services.findAll();
        model.addAttribute("allDetails", allDetails);
        return "admin/showAll";
    }

    @GetMapping("adminPage/createUser")
    public String createUser() {
        return "admin/createUser";
    }

    @PostMapping("adminPage/userCreated")
    public String createUser(@ModelAttribute Entitys userDetails) {


        Entitys userDetail = new Entitys(
                userDetails.getName(),
                userDetails.getEmail(),
                passwordEncoder.encode(userDetails.getPassword()),
                "USER"
        );
        if (!(services.nameIsExist(userDetails.getName()))) {

            services.save(userDetail);
        } else {
            return "redirect:/adminPage/createUser?userError";

        }
        return "redirect:/adminPage";
    }

    @GetMapping("adminPage/createAdmin")
    public String createAdmin() {
        return "admin/createAdmin";
    }

    @PostMapping("adminPage/adminCreated")
    public String createAdmin(@ModelAttribute Entitys userDetails) {
        Entitys userDetail = new Entitys(
                userDetails.getName(),
                userDetails.getEmail(),
                passwordEncoder.encode(userDetails.getPassword()),
                "ADMIN"
        );
        services.save(userDetail);
        return "redirect:/adminPage";
    }

    @GetMapping("adminPage/edit")
    public String edit(Model model) {
        List<Entitys> allDetails = services.findAll();
        model.addAttribute("allDetails", allDetails);

        return "admin/editPage";
    }

    @PostMapping("adminPage/editing")
    public String editing(@ModelAttribute Entitys user, Model model) {

        model.addAttribute("name", user.getName());
        model.addAttribute("password", "None");
        model.addAttribute("email", user.getEmail());
        model.addAttribute("role", user.getRole());
        return "admin/editingPage";
    }

    @PostMapping("adminPage/edited")
    public String edited(@ModelAttribute Entitys userDetails) {

        System.out.println(userDetails.getName());

        String name = userDetails.getName();
        String email = userDetails.getEmail();
        String password = userDetails.getPassword();
        String role = userDetails.getRole();

        Entitys updatedDetail = services.findByName(name);

        updatedDetail.setName(name);
        updatedDetail.setEmail(email);
        updatedDetail.setRole(role);

        if (!(password.equals("None"))) {
            updatedDetail.setPassword(
                    passwordEncoder.encode(password));
        }
        System.out.println(updatedDetail);

        services.save(updatedDetail);

        return "redirect:/adminPage/edit";
    }

    @GetMapping("adminPage/delete")
    public String deleteUser(Model model) {
        List<Entitys> allDetails = services.findAll();
        model.addAttribute("allDetails", allDetails);

        return "admin/deletePage";
    }

    @PostMapping("adminPage/deleted")
    public String deleted(@RequestParam("userId") Integer id) {

        services.deleteById(id);

        return "redirect:/adminPage/delete";
    }

    @GetMapping("adminPage/deleteAll")
    public String deleteAll(Model model) {
        List<Entitys> allDetails = services.findAll();
        model.addAttribute("allDetails", allDetails);

        return "admin/deleteAll";
    }

    @PostMapping("adminPage/deletedAll")
    public String deletedAll() {

        services.deleteAllUsers();
        return "redirect:/adminPage";
    }

}
