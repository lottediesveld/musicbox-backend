package com.musicbox.user.services;

import com.google.gson.Gson;
import com.musicbox.user.common.dto.RegisterDTO;
import com.musicbox.user.common.dto.UserRegisterDTO;
import com.musicbox.user.common.models.User;
import com.musicbox.user.common.utils.AuthenticationUtils;
import com.musicbox.user.eventbus.MessageProducer;
import com.musicbox.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.musicbox.user.common.security.Role.USER;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private MessageProducer messageProducer;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, MessageProducer messageProducer) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.messageProducer = messageProducer;
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
            return "error";
        } else if (!userRepository.existsByEmail(user.getEmail()) || !userRepository.existsByUsername(user.getUsername())) {
            var updateUser = modelMapper.map(user, User.class);
            User newUser = new User(updateUser.getFirstName(), updateUser.getLastName(), updateUser.getUsername(), updateUser.getPassword(), updateUser.getEmail(), true, true, true, true, USER.getGrantedAuthorities());
            userRepository.save(newUser);

            if(userRepository.findUserByUsername(newUser.getUsername()) != null){
                Gson gson = new Gson();
                RegisterDTO dto = new RegisterDTO(newUser.getId(), newUser.getUsername());
                messageProducer.newPlaylistForUser(gson.toJson(dto));
                return "saved";
            }
            return "something went wrong";

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
        Gson gson = new Gson();
        messageProducer.deletePlaylistFromUser(gson.toJson(user.getId()));

        if (userRepository.findUserByUsername(username) == null){
            return "successful";
        } else {
            return "unsuccessful";
        }
    }

    public User getByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        System.out.println(user.getId());
        return user;
    }

    public boolean changePassword(User user, String oldPass, String newPass) {
        if (passwordEncoder.encode(oldPass) != user.getPassword())
            return false;
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
        return true;
    }
}
