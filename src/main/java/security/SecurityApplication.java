package security;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import security.db.repository.UserRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}

@AllArgsConstructor
@Component
class StartupService {
    private final UserRepository repository;

    @PostConstruct
    public void init() {
        repository.findAll().forEach(System.out::println);
    }
}
