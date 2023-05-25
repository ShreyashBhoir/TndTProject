package com.maxxton.tour.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.maxxton.tour.entities.JwtRequest;
import com.maxxton.tour.entities.JwtResponse;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.CustomerUserDetailsService;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepo userRepo;


    @Autowired
    private CustomerUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder pe;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        System.out.println("Inside Controller");
        System.out.println(jwtRequest);
        try {
        	System.out.println(jwtRequest.getUsername()+"-----"+jwtRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));


        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials1");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials2");
        }


        //fine area..
        System.out.println(jwtRequest.getUsername());
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println( userDetails);
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);

        //{"token":"value"}

        return ResponseEntity.ok(new JwtResponse(token));

    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception {

        System.out.println("Inside Controller");
        user.setRoles("ROLE_USER");
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        user.setIsActive(user.getIsActive());
        user.setPassword(user.getPassword());
        user.setUserName(user.getUserName());
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setMobileno(user.getMobileno());
        user.setPassword(pe.encode(user.getPassword()));
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    
}