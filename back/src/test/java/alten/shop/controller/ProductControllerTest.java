package alten.shop.controller;

import alten.shop.model.Product;
import alten.shop.repository.ProductRepository;
import alten.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class ProductControllerTest {

    @Test
    void getOne_returnsProduct() {
        ProductRepository repo = Mockito.mock(ProductRepository.class);
        Product p = new Product();
        p.setId(1L);
        p.setName("Test");
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(p));
        ProductService service = new ProductService(repo);
        ProductController controller = new ProductController(service);

        ResponseEntity<Product> res = controller.getOne(1L);
        assertThat(res.getBody()).isNotNull();
        assertThat(res.getBody().getName()).isEqualTo("Test");
    }


}
