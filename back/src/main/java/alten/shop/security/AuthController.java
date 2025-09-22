package alten.shop.security;

import alten.shop.dto.AuthResponse;
import alten.shop.dto.LoginRequest;
import alten.shop.dto.RegisterRequest;
import alten.shop.model.User;
import alten.shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder encoder;


    public AuthController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;

    }

    @GetMapping("/account")
    public String register(@RequestParam String username,@RequestParam String password,@RequestParam String email) {
        return "in account : " + username + " - " + email;
    }

    @GetMapping("/token")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "in the token : " + username;
    }
}
