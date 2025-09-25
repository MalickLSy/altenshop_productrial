package alten.shop.config;


import alten.shop.model.User;
import alten.shop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            userRepo.findByEmail("admins@admin.com").orElseGet(() -> {
               final User admin = User.builder()
                        .username("admin")
                        .firstname("Admin")
                        .email("admins@admin.com")
                        .password(encoder.encode("admin2025"))
                        .build();
                return userRepo.save(admin);
            });
        };
    }
}
