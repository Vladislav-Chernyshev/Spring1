package ru.chernyshev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chernyshev.persist.User;
import ru.chernyshev.persist.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        System.out.println("Метод postConstruct отработал");
    }

    public void insert(User user) {
        if (user.getRole().equals("ADMIN") || user.getRole().equals("GUEST")) {
            this.userRepository.insert(user);
        }else {
            throw new IllegalArgumentException("Incorrect role");
        }
    }

    public int findAll() {
        return userRepository.findAll().size();
    }


}
