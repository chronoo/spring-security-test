package security;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import security.entity.SimpleUser;
import security.repository.UserRepository;

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
        if (repository.count() == 0) {
            SimpleUser defaultUser = new SimpleUser("alex", "123", "read");
            repository.save(defaultUser);
        }
        repository.findAll().forEach(System.out::println);
    }
}
