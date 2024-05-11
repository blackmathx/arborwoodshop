package com.arborwoodshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"", "/", "admin-dashboard"})
    public String admin() {
        return "admin/admin-dashboard";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "support-tasks")
    public String todoList() {
        return "admin/admin-dashboard";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "manage-users")
    public String manageUser() {
        return "admin/admin-dashboard";
    }
}
