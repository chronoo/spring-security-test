package security.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService executorService = new DelegatingSecurityContextExecutorService(
            Executors.newCachedThreadPool()
        );

        try {
            return "Hi, " + executorService.submit(task).get() + "!";
        } finally {
            executorService.shutdown();
        }
    }
}
