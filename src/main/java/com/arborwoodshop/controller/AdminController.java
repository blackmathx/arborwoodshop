package com.arborwoodshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"", "/", "dashboard"})
    public String admin() {
        // TODO implement a users/subscribers/items count view and metadata for admin
        return "admin/dashboard";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "support-tasks")
    public String todoList() {
        // TODO implement an it-support system tasks list
        return "admin/dashboard";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "manage-users")
    public String manageUser() {
        // TODO implement a way to disable/delete accounts and items for the admin
        return "admin/dashboard";
    }

}
