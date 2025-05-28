package de.computerspecs.backend.sonstiges;

import de.computerspecs.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * creation of admin account
 */

@Component
public class CreateAdminAccount implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * creates admin account and automatically runs when system gets online
     * @param args
     * @throws Exception
     */

    @Override
    public void run(String... args) throws Exception {
        String username = "admin";
        String password = "admin";

        if (!userRepository.existsByUsername(username)) {
            userRepository.createAdminAccount(username, bCryptPasswordEncoder.encode(password));
            System.out.println("Admin account created.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}
