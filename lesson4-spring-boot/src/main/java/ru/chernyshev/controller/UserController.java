package ru.chernyshev.controller;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.model.dto.UserDto;
import ru.chernyshev.model.User;
import ru.chernyshev.repository.UserRepository;
import ru.chernyshev.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

//    @GetMapping
//    public String listPage(@RequestParam Optional <String> usernameFilter, Model model) {
//        if (usernameFilter.isEmpty() || usernameFilter.get().isBlank()){
//            model.addAttribute("users", userRepository.findAll());
//        }else {
//            model.addAttribute("users", userRepository.usersByUsername("%" + usernameFilter.get() + "%"));
//        }
//        return "user";
//    }

//    @GetMapping
//    public String listPage(@RequestParam(required = false) String usernameFilter,
//                           @RequestParam(required = false) String emailFilter,
//                           Model model) {
//        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
//        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";
//        model.addAttribute("users", userRepository.usersByUsername(usernameFilter, emailFilter));
//
//        return "user";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter,
                           @RequestParam(required = false) String emailFilter,
                           Model model) {


        model.addAttribute("users", service.findAllByFilter(usernameFilter, emailFilter));

        return "user";
    }


    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", service.findUserById(id));
        return "user_form";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("matchingPassword", "Password not match");
            return "user_form";
        }
        service.save(user);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDto user) {
        service.save(user);
        return "redirect:/user";
    }



    @DeleteMapping("{id}")
    public String deleteUserById(User user) {
        service.deleteUserById(user.getId());
        return "redirect:/user";
    }

}
