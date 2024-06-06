package com.project.stackoverflow.controllers;

import com.project.stackoverflow.dtos.AuthenticationResponse;
import com.project.stackoverflow.entities.User;
import com.project.stackoverflow.repositories.UserRepository;
import com.project.stackoverflow.utils.JwtUtil;
import com.project.stackoverflow.dtos.AuthenticationRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX="Bearer ";

    public static final String HEADER_STRING="Authorization";


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authentication")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("incorrect credentials");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"user is not found ");
            return ;
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt=jwtUtil.generateToken(userDetails.getUsername());

        if(optionalUser.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("userId",optionalUser.get().getId())
                    .toString()
            );
        }

        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.setHeader("Access-Control-Allow-Headers","Authorization,X-PINGOTHER,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);


    }
}
