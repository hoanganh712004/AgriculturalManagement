package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.filters.JwtTokenFilter;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.user.UserCustomerService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovePageRoleLoginController {

    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserCustomerService userCustomerService;

    @GetMapping("/movePageRole")
    public String MovePageRole(Model model) {

        String token = (String) session.getAttribute("authToken");

        if (token == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Giải mã token
        Claims claims = jwtTokenFilter.decodeToken(token);
        if (claims == null) {
            session.invalidate();
            return "redirect:/login";
        }

        // Lấy thông tin người dùng từ claims
        String email = claims.getSubject();
        User userEntity = userCustomerService.loadUserByEmail(email);

        if (userEntity == null) {
            session.invalidate();
            return "redirect:/login";
        } else {
            if (userEntity.getRole().getRoleName().equals("admin")) {
                return "redirect:/login";
            } else if (userEntity.getRole().getRoleName().equals("user")) {

                UserResponse userResponse = userCustomerService.getUser(userEntity);
                session.setAttribute("account", userResponse);
                session.setAttribute("accountId", userResponse.getUserID());
                session.setAttribute("accountImage", userResponse.getImageUrl());
                System.out.println(session.getAttribute("accountId").toString());

                return "redirect:/home";
            } else if (userEntity.getRole().getRoleName().equals("blogger")) {
                return "redirect:/login";
            } else if (userEntity.getRole().getRoleName().equals("seller")) {
                UserResponse userResponse = userCustomerService.getUser(userEntity);
                session.setAttribute("account", userResponse);
                return "redirect:/seller-dashboard";
            } else if (userEntity.getRole().getRoleName().equals("warehourestaff")) {
                return "redirect:/login";
            }

            session.invalidate();
            return "redirect:/login";
        }
    }

}