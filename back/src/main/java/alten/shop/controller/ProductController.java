package alten.shop.controller;

import alten.shop.model.Product;
import alten.shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id) {
        return service.get(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean isAdmin(Authentication auth) {
        return auth != null && "admin@admin.com".equalsIgnoreCase(auth.getName());
    }

    @PostMapping
    public ResponseEntity<?> create(Authentication auth, @RequestBody @Valid Product p) {
        if (!isAdmin(auth)) return ResponseEntity.status(403).body("Accès refusé");
        return ResponseEntity.ok(service.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(Authentication auth, @PathVariable Long id, @RequestBody @Valid Product p) {
        if (!isAdmin(auth)) return ResponseEntity.status(403).body("Accès refusé");
        if (!service.exists(id)) return ResponseEntity.notFound().build();
        p.setId(id);
        return ResponseEntity.ok(service.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Authentication auth, @PathVariable Long id) {
        if (!isAdmin(auth)) return ResponseEntity.status(403).body("Accès refusé");
        if (!service.exists(id)) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok("Produit supprimé");
    }
}
