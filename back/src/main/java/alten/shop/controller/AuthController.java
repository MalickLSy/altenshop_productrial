package alten.shop.controller;

import alten.shop.dto.AuthResponse;
import alten.shop.dto.LoginRequest;
import alten.shop.dto.RegisterRequest;
import alten.shop.model.User;
import alten.shop.security.JwtService;
import alten.shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthController(UserService userService, PasswordEncoder encoder, JwtService jwtService) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/account")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest r) {
        if (userService.findByEmail(r.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already used");
        }
        User u = new User();
        u.setUsername(r.username());
        u.setFirstname(r.firstname());
        u.setEmail(r.email());
        u.setPassword(r.password());
        userService.createUser(u);
        return ResponseEntity.ok("Compte créé");
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest r) {
        var opt = userService.findByEmail(r.email());
        if (opt.isEmpty()) return ResponseEntity.status(401).build();
        var user = opt.get();
        if (!encoder.matches(r.password(), user.getPassword())) return ResponseEntity.status(401).build();
        String token = jwtService.generateToken(user.getEmail(), user.getId());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
