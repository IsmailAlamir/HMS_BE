package com.example.hospitalManagement.HMS.auth;


import com.example.hospitalManagement.HMS.Domain.Patient;
import com.example.hospitalManagement.HMS.Domain.token.Token;
import com.example.hospitalManagement.HMS.Domain.token.TokenRepository;
import com.example.hospitalManagement.HMS.Domain.token.TokenType;
import com.example.hospitalManagement.HMS.Domain.user.Role;
import com.example.hospitalManagement.HMS.Domain.user.User;
import com.example.hospitalManagement.HMS.config.JwtService;
import com.example.hospitalManagement.HMS.repository.PatientRepository;
import com.example.hospitalManagement.HMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service

@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    public AuthenticationResponse patientRegister(Patient patient) {
//        patientRepository.save(user);

        var newUser = new Patient();
        newUser.setFirstName(patient.getFirstName());
        newUser.setLastName(patient.getLastName());
        newUser.setLocation(patient.getLocation());
        newUser.setPhone(patient.getPhone());
        newUser.setUsername(patient.getUsername());
        newUser.setEmail(patient.getEmail());
        newUser.setPassword(passwordEncoder.encode(patient.getPassword()));
        newUser.setRole(Role.PATIENT);

        var savedUser = patientRepository.save(newUser);

        var jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);

        var authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtToken);

        return authenticationResponse;
    }




    public AuthenticationResponse adminRegister(User user) {

        var newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .location(user.getLocation())
                .phone(user.getPhone())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();

        var savedUser = userRepository.save(newUser);

        var jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticateRequest request) { // the user can log in using email or username
        User user;

        if (request.getEmail() == null) {
            user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow();
        } else {
            user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        request.getPassword()
                )
        );

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserToken(user);
        saveUserToken(user,accessToken );
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthenticationResponse refresh(String refreshToken) {
        String email = jwtService.extractUserEmail(refreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (jwtService.isTokenValid(refreshToken, userDetails) && userDetails != null) {
            String newAccessToken = jwtService.generateToken(userDetails);
            String newRefreshToken = jwtService.generateRefreshToken(userDetails);

            return AuthenticationResponse
                    .builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        }
        return null;
    }

    private void revokeAllUserToken(User user){
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(t-> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    private void saveUserToken(User user, String jwtToken) { // save the token in DB
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }




}
