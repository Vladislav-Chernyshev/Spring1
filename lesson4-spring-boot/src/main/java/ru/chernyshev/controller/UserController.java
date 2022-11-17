package ru.chernyshev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.model.dto.UserDto;
import ru.chernyshev.model.User;
import ru.chernyshev.service.RoleService;
import ru.chernyshev.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

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
                           @RequestParam(required = false) Optional<Integer> page,
                           @RequestParam(required = false) Optional<Integer> size,
                           @RequestParam(required = false) Optional<String> sortField,
                           Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");


        model.addAttribute("users", userService.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue));

        return "user";
    }


    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userService.findUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
        return "user_form";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("matchingPassword", "Password not match");
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDto user) {
        userService.save(user);
        return "redirect:/user";
    }


    @DeleteMapping("{id}")
    public String deleteUserById(User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/user";
    }

}
