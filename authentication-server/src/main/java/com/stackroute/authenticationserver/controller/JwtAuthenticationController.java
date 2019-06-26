package com.stackroute.authenticationserver.controller;

import com.stackroute.authenticationserver.config.JwtTokenUtil;
import com.stackroute.authenticationserver.model.CheckResponse;
import com.stackroute.authenticationserver.model.JwtRequest;
import com.stackroute.authenticationserver.model.JwtResponse;

import com.stackroute.authenticationserver.model.User;
import com.stackroute.authenticationserver.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println("inside createAuthenticationToken");

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
        System.out.println("inside registration controller");
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @GetMapping(value = "/checkUser" )
    public ResponseEntity<?> checkUser() {
        String s = "ahis is a registered User";
        return ResponseEntity.ok(new CheckResponse(s));
    }


    private void authenticate(String username, String password) throws Exception {
        System.out.println("inside authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
