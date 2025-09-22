package alten.shop.controller;


import alten.shop.model.Product;
import alten.shop.model.User;
import alten.shop.repository.ProductRepository;
import alten.shop.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public UserController(UserRepository userRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    private User getCurrentUser(Authentication auth) {
        return userRepo.findByEmail(auth.getName()).orElseThrow();
    }

    @GetMapping("/me")
    public ResponseEntity<User> currentUser(Authentication auth) {
        return ResponseEntity.ok(getCurrentUser(auth));
    }

}
