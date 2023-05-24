package com.example.hospitalManagement.HMS.Controller;


import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")

    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "POST:: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "DELETE:: admin controller";
    }

    // create any user
    @ResponseBody
    @PostMapping("/new-user")
    @PreAuthorize("hasAuthority('admin:create')")
    public User createNewUser(@RequestBody User user){
//        if (user.getRole() == Role.PATIENT) {
//            throw new IllegalArgumentException("Invalid role. 'PATIENT' role is not allowed for this endpoint.");
//        }
        return userRepository.save(user);

    }

}
