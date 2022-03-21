package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import security.repository.UserRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}

@Component
class Start{
    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void init() {
        repository.findAll().forEach(System.out::println);
    }
}