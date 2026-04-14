package dev.kisanhelp.project_kh.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.kisanhelp.project_kh.dto.request.RegisterRequest;
import dev.kisanhelp.project_kh.dto.response.JwtResponse;
import dev.kisanhelp.project_kh.dto.response.UserDetailsData;
import dev.kisanhelp.project_kh.dto.response.UserDetailsResponse;
import dev.kisanhelp.project_kh.entity.AppUser;
import dev.kisanhelp.project_kh.exception.UserAlreadyExistsException;
import dev.kisanhelp.project_kh.exception.UserNotFoundException;
import dev.kisanhelp.project_kh.repository.UserRepository;
import dev.kisanhelp.project_kh.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<JwtResponse> register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        AppUser user = new AppUser();

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setState(request.getState());
        user.setCity(request.getCity());
        user.setLandArea(request.getLandArea());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<UserDetailsResponse> getUserByName(String userName) {
        Optional<AppUser> user = userRepository.findByEmail(userName);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found: " + userName);
        }

        UserDetailsResponse userResponse = new UserDetailsResponse(
                "success",
                LocalDateTime.now(),
                getUserData(user.get()));

        return ResponseEntity.ok(userResponse);
    }

    private UserDetailsData getUserData(AppUser user) {

        return new UserDetailsData(
                user.getUserName(),
                user.getEmail(),
                user.getState(),
                user.getCity(),
                user.getLandArea());
    }
}
