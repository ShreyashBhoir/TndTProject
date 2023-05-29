package com.maxxton.tour.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.maxxton.tour.DTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.maxxton.tour.entities.EmailDetails;
import com.maxxton.tour.entities.JwtRequest;
import com.maxxton.tour.entities.JwtResponse;
import com.maxxton.tour.entities.User;
import com.maxxton.tour.helper.JwtUtil;
import com.maxxton.tour.repository.UserRepo;
import com.maxxton.tour.service.CustomerUserDetailsService;
import com.maxxton.tour.service.EmailService;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    
    @Autowired
    private EmailService emailService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginDTO> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

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
         
         String username=jwtRequest.getUsername();
         System.out.println(username);
         User user=userRepo.findByEmail(username);
         System.out.println(user.getRoles());
         LoginDTO u=new LoginDTO();
         u.setToken(token);
         u.setRoles(user.getRoles());
        return ResponseEntity.ok(u);

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
        EmailDetails details=new EmailDetails();
        details.setRecipient(user.getEmail());
        details.setMsgBody("Hey \n \n This is Registration email \n\n Thank You For Registering");
        details.setSubject("Tour Registraion Email");
        String status= emailService.sendSimpleMail(details);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
    
}