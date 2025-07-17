package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @GetMapping("/logoutUser")
    public String logout(HttpServletRequest request, Model model) {
        String emailCookieSaved = "";
        String passwordCookieSaved = "";
        Cookie[] cookies = request.getCookies(); // get data cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("COOKIE_EMAIL")) {
                    emailCookieSaved = cookie.getValue();
                }
                if (cookie.getName().equals("COOKIE_PASSWORD_FAKE")) {
                    passwordCookieSaved = cookie.getValue();
                }
            }
        }

        if (!(emailCookieSaved.equals("") || passwordCookieSaved.equals(""))) {
            model.addAttribute("emailCookieSaved", emailCookieSaved);
            model.addAttribute("passwordCookieSaved", passwordCookieSaved);
        } else {
            session.invalidate();
        }


        return "FrontEnd/Home/login";

    }
}
