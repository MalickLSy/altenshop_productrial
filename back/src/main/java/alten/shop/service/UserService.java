package alten.shop.service;

import alten.shop.model.User;
import alten.shop.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User createUser(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return userRepo.save(u);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
