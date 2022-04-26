package az.iktlab.securityservice.controller;

import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.exception.UserNotFoundException;
import az.iktlab.securityservice.security.MyUserDetailsService;
import az.iktlab.securityservice.security.data.AuthenticationRequest;
import az.iktlab.securityservice.security.data.AuthenticationResponse;
import az.iktlab.securityservice.security.util.JwtUtil;
import az.iktlab.securityservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST})
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
        } catch (AuthenticationException e) {
            throw new UserNotFoundException("User Not Found");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean signUp(@RequestBody AccountRequestDTO request) {
        return accountService.addUser(request);
    }
}
