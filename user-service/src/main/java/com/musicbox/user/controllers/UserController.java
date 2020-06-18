package com.musicbox.user.controllers;
import com.google.gson.Gson;
import com.musicbox.user.common.dto.UserRegisterDTO;
import com.musicbox.user.common.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.musicbox.user.repositories.UserRepository;
import com.musicbox.user.services.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = REST_URI_Constant.newUser)
    public @ResponseBody
    String userRegister(@RequestBody String user) throws Exception {
        Gson gson = new Gson();
        var userObject = gson.fromJson(user, UserRegisterDTO.class);

        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));

        return gson.toJson(userService.newUser(userObject));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = REST_URI_Constant.id, method = RequestMethod.GET)
    public @ResponseBody
    Long getId(@RequestParam("email") String email){
        return userService.getId(email);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = REST_URI_Constant.deleteUser, method = RequestMethod.DELETE)
    public @ResponseBody
    String delete() {
        Gson gson = new Gson();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = (String) auth.getPrincipal();

        return gson.toJson(userService.deleteUser(username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = REST_URI_Constant.allUsers, method = RequestMethod.GET)
    public @ResponseBody
    Iterable<User> allUsers() {
        return userService.alluser();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = REST_URI_Constant.currentUser, method = RequestMethod.GET)
    public @ResponseBody
    User current() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String username = (String) auth.getPrincipal();
        User user = userRepository.findUserByUsername(username);

        for(User usertest: userRepository.findAll()){
            System.out.println(usertest.getId());
            System.out.println(usertest.getUsername());
        }

        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = REST_URI_Constant.changePassword, method = RequestMethod.POST)
    public @ResponseBody
    String changePassword(@RequestBody String newPass) {
        Gson gson = new Gson();
        return gson.toJson(userService.changePassword(current(), newPass));
    }

    @RequestMapping(value = REST_URI_Constant.userByUsername, method = RequestMethod.GET)
    public @ResponseBody
    User getUserByUsername(@RequestParam("username") String username) {
        return userService.getByUsername(username);
    }
}
