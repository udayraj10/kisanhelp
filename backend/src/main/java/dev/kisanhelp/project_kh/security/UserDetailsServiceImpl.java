package dev.kisanhelp.project_kh.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dev.kisanhelp.project_kh.entity.AppUser;
import dev.kisanhelp.project_kh.exception.UserNotFoundException;
import dev.kisanhelp.project_kh.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<AppUser> user = userRepository.findByEmail(userName);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());
    }

}
