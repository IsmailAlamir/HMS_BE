package com.example.hospitalManagement.HMS.Domain.token;

import com.example.hospitalManagement.HMS.Domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //generate getter and setter etc..
@Builder //help build object "generates a builder pattern implementation"
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id ;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;


}
