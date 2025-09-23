package alten.shop.integration;


import alten.shop.dto.RegisterRequest;
import alten.shop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepo;

    @Test
    void register_and_user_saved() {
        var req = new RegisterRequest("admin","Admin","admin@admin.com","admin2025");
        ResponseEntity<String> res = restTemplate.postForEntity("/account", req, String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepo.findByEmail("admin@admin.com")).isPresent();
    }
}
