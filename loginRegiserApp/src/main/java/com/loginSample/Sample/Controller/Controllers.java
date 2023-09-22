package com.loginSample.Sample.Controller;

import com.loginSample.Sample.Entity.Entitys;
import com.loginSample.Sample.Service.Services;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.Soundbank;

@Controller    
public class Controllers {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Services services;

    @GetMapping("/")
    public String root() {
        return "login&register";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                if (userDetails.getAuthorities().contains("ADMIN")) {
                    return "redirect:/adminPage";
                } else if (userDetails.getAuthorities().contains("USER")) {
                    return "redirect:/userPage";
                }

            }
        }

        return "loginPage";
    }

    @GetMapping("/userPage")
    public String home(Model model) {
        Authentication ath = SecurityContextHolder.getContext().getAuthentication();

        String userName = ath.getName();
        model.addAttribute("userName",userName);
        return "userPage";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        System.out.println("Registration....");
        return "registrationPage";
    }

    @PostMapping("/registed")
    public String registed(@ModelAttribute("user") Entitys userInfo, Model model) {
        if (!(services.nameIsExist(userInfo.getName()))) {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            services.save(userInfo);
        } else {

            return "redirect:/registration?userError";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
