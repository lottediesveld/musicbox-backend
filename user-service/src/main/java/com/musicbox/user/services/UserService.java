package com.musicbox.user.services;

import com.musicbox.user.common.dto.UserRegisterDTO;
import com.musicbox.user.common.models.User;
import com.musicbox.user.common.utils.AuthenticationUtils;
import com.musicbox.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public String newUser(UserRegisterDTO user) throws Exception {
        final Pattern Email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (user == null) throw new IllegalArgumentException("The user object is not allowed to be null.");

        if (user.getEmail().isEmpty() || user.getEmail() == null) {
            throw new IllegalArgumentException("Email can`t be empty or null");
        }

        if (user.getPassword().isEmpty() || user.getPassword() == null) {
            throw new IllegalArgumentException("Password can`t be empty or null");
        }
        if (!Email.matcher(user.getEmail()).find()) {
            throw new IllegalArgumentException("The email should be a valid email address.");
        }
        if (userRepository.existsByEmail(user.getEmail()) && userRepository.existsByUsername(user.getUsername())) {

            var userEntity = userRepository.findUserByUsername(user.getUsername());

            var updateUser = modelMapper.map(userEntity, User.class);

            updateUser.setAccountNonLocked(true);
            updateUser.setAccountNonExpired(true);
            updateUser.setCredentialsNonExpired(true);

            updateUser.setPassword(new AuthenticationUtils().encode(user.getPassword()));
            userRepository.save(updateUser);
            updateUser.setPassword(null);
            return "saved";
        } else if (!userRepository.existsByEmail(user.getEmail()) || !userRepository.existsByUsername(user.getUsername())) {
            var updateUser = modelMapper.map(user, User.class);
            userRepository.save(updateUser);
            return "saved";
        } else {
            throw new Exception("Wrong combination");
        }
    }

    public Long getId(String email){
        return userRepository.findIdByEmail(email);
    }

    public Iterable<User> alluser() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public String deleteUser(String username) {
        User user = userRepository.findUserByUsername(username);
        userRepository.delete(user);

        if (userRepository.findUserByUsername(username) == null){
            return "successful";
        } else {
            return "unsuccessful";
        }
    }

    public User getByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean changePassword(User user, String oldPass, String newPass) {
        if (passwordEncoder.encode(oldPass) != user.getPassword())
            return false;
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
        return true;
    }
}
