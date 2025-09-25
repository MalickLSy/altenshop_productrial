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

    @GetMapping("/cart")
    public ResponseEntity<List<Product>> getCart(Authentication auth) {
        User u = getCurrentUser(auth);
        return ResponseEntity.ok(u.getCart());
    }

    @PostMapping("/cart/{productId}")
    public ResponseEntity<?> addToCart(Authentication auth, @PathVariable Long productId) {
        User u = getCurrentUser(auth);
        Product p = productRepo.findById(productId).orElseThrow();
        u.getCart().add(p);
        userRepo.save(u);
        return ResponseEntity.ok(u.getCart());
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<?> removeFromCart(Authentication auth, @PathVariable Long productId) {
        User u = getCurrentUser(auth);
        u.getCart().removeIf(p -> p.getId().equals(productId));
        userRepo.save(u);
        return ResponseEntity.ok(u.getCart());
    }

    @GetMapping("/wishlist")
    public ResponseEntity<List<Product>> getWishlist(Authentication auth) {
        User u = getCurrentUser(auth);
        return ResponseEntity.ok(u.getWishlist());
    }

    @PostMapping("/wishlist/{productId}")
    public ResponseEntity<?> addToWishlist(Authentication auth, @PathVariable Long productId) {
        User u = getCurrentUser(auth);
        Product p = productRepo.findById(productId).orElseThrow();
        u.getWishlist().add(p);
        userRepo.save(u);
        return ResponseEntity.ok(u.getWishlist());
    }

    @DeleteMapping("/wishlist/{productId}")
    public ResponseEntity<?> removeFromWishlist(Authentication auth, @PathVariable Long productId) {
        User u = getCurrentUser(auth);
        u.getWishlist().removeIf(p -> p.getId().equals(productId));
        userRepo.save(u);
        return ResponseEntity.ok(u.getWishlist());
    }
}
