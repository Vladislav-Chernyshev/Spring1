package ru.chernyshev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.persist.User;
import ru.chernyshev.persist.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

//    @GetMapping
//    public String listPage(@RequestParam Optional <String> usernameFilter, Model model) {
//        if (usernameFilter.isEmpty() || usernameFilter.get().isBlank()){
//            model.addAttribute("users", userRepository.findAll());
//        }else {
//            model.addAttribute("users", userRepository.usersByUsername("%" + usernameFilter.get() + "%"));
//        }
//        return "user";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter, Model model) {
        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
        model.addAttribute("users", userRepository.usersByUsername(usernameFilter));

        return "user";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User(""));
        return "user_form";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "user_form";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("matchingPassword", "Password not match");
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(User user) {
        userRepository.deleteById(user.getId());
        return "redirect:/user";
    }

}
