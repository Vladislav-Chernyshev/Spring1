package ru.chernyshev.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.model.User;
import ru.chernyshev.model.dto.UserDto;
import ru.chernyshev.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService service;

    @GetMapping
    public List<UserDto> listPage(@RequestParam(required = false) String usernameFilter,
                                  @RequestParam(required = false) String emailFilter,
                                  @RequestParam(required = false) Optional<Integer> page,
                                  @RequestParam(required = false) Optional<Integer> size,
                                  @RequestParam(required = false) Optional<String> sortField,
                                  Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        Page<UserDto> allByFilter = service.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue);
        List<UserDto> users = allByFilter.get().collect(Collectors.toList());

        return users;
    }

    @GetMapping("/{id}")
    public UserDto form(@PathVariable("id") long id, Model model) {
        UserDto userDto = service.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userDto;
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Created user shouldn't have id");
        }
        service.save(user);
        return user;
    }


}
