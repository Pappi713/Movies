package com.greenfoxacademy.demo.controller;

import com.greenfoxacademy.demo.model.*;
import com.greenfoxacademy.demo.service.MovieService;
import com.greenfoxacademy.demo.service.MyUserDetailsService;
import com.greenfoxacademy.demo.util.JwtUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MovieService movieService;




    @GetMapping("/")
    private String home() {
        return ("<h1> Welcome </h1>");
    }

    @GetMapping("/user")
    private String user() {
        return ("<h1> Welcome User </h1>");
    }

    @GetMapping("/admin")
    private String admin() {
        return ("<h1> Welcome Admin </h1>");
    }

    @GetMapping("/hello")
    private String hello() {
        return ("Hello World!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @ResponseBody
    @GetMapping("/genres")
    public ResponseEntity<Genres> getGenres () throws IOException {
        return new ResponseEntity<Genres>((Genres) movieService.callGenres(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/PopularTVShows")
    public ResponseEntity<TVCollection> getTVShows() throws  IOException {
        return  new ResponseEntity<>((TVCollection) movieService.getTVShows(),HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/country")
    public ResponseEntity<MostCommonOriginalCountry> mostCommonOriginCountry() throws  IOException {
        return ResponseEntity.ok(new MostCommonOriginalCountry(movieService.mostCommonOriginCountry()));
    }
}
