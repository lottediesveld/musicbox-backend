package com.musicbox.user;

import com.musicbox.user.common.models.User;
import com.musicbox.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static com.musicbox.user.common.security.Role.USER;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,RepositoryRestMvcAutoConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            var user1 = new User( "Victor", "Leeuwen", "victory", passwordEncoder.encode("test2"), "test@test.com", true, true, true, true, USER.getGrantedAuthorities()); //default
            var user2 = new User("Lotte", "Diesveld", "lagora", passwordEncoder.encode("test2"), "test2@test.com", true, true, true, true, USER.getGrantedAuthorities());
            var user3 = new User("Joris", "Wijnen", "qarnix", passwordEncoder.encode("test2"), "test3@test.com", true, true, true, true, USER.getGrantedAuthorities());

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        };
    }

    @Configuration
    class RestTemplateConfig {

        // Create a bean for restTemplate to call services
        @Bean
        @LoadBalanced        // Load balance between service instances running at different ports.
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
