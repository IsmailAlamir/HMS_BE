package com.example.hospitalManagement.HMS.auth;

import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.repository.PatientRepository;
import com.example.hospitalManagement.HMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;



    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createNewUser(@RequestBody Patient patient){

        return ResponseEntity.ok(authenticationService.patientRegister(patient));
    }


    @PostMapping("/admin/register")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User user
    ){
        return ResponseEntity.ok(authenticationService.adminRegister(user));
    }
    // It receives a RegisterRequest object in the request body
    // and returns a ResponseEntity<AuthenticationResponse> containing the response from the service.register() method.



    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    // It receives an AuthenticateRequest object in the request body
    // and returns a ResponseEntity<AuthenticationResponse> containing the response from the service.authenticate() method.


    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(authenticationService.refresh(request.getRefreshToken()));
    }

}
