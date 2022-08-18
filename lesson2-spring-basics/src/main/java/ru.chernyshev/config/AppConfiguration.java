package ru.chernyshev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.chernyshev.UserService;
import ru.chernyshev.persist.UserRepository;
import ru.chernyshev.persist.UserRepositoryImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }
}
