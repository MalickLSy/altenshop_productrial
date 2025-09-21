package alten.shop.service;

import alten.shop.model.Product;
import alten.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> all() { return repo.findAll(); }
    public Optional<Product> get(Long id) { return repo.findById(id); }
    public Product save(Product p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
    public boolean exists(Long id) { return repo.existsById(id); }
}
