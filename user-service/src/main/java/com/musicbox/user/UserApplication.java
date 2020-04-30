package com.musicbox.user;

import com.musicbox.user.common.models.User;
import com.musicbox.user.common.utils.AuthenticationUtils;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            AuthenticationUtils auth = new AuthenticationUtils();
            BufferedReader reader = new BufferedReader(new FileReader("enexis_electricity_01012010_mod.csv"));
            // Do one readLine to skip the first line of column headers
            reader.readLine();
            String value1 = reader.readLine();
            String value2 = reader.readLine();
            String[] data1 = value1.split(",");
            String[] data2 = value2.split(",");

            var user1 = new User((long) 1, "victor", "leeuwen", "victory", passwordEncoder.encode("test2"), "test@test.com"); //default
            //var user2 = new User("Piet","Pieters",passwordEncoder.encode("test1"),"test@test.nl", "0773086060", "0687654321",data2[CsvValues.ZIPCODE.getValue()],data2[CsvValues.STREET.getValue()], data2[CsvValues.CITY.getValue()], data2[CsvValues.HOUSE_NUMBER.getValue()], "007",true,true,true,true, USER.getGrantedAuthorities()); //default

            user1 = userRepository.save(user1);
            //  user2 = userRepository.save(user2);
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
