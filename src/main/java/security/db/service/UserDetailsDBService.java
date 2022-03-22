package security.db.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.db.entity.SimpleUser;
import security.db.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserDetailsDBService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public SimpleUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
