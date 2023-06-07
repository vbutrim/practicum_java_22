package com.vbutrim.filmorate.api;

import com.vbutrim.filmorate.user.User;
import com.vbutrim.filmorate.user.UserService;
import com.vbutrim.filmorate.user.UserToCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public CreateUser.ResponseDto create(@Valid @RequestBody final CreateUser.InputDto user) {
        log.info("Creating user {}", user);
        return new CreateUser.ResponseDto(userService.save(user.asUserToCreate()));
    }

    @GetMapping("/users")
    public List<CreateUser.ResponseDto> getAll() {
        final List<User> users = userService.getAll();
        log.info("Get all users {}", users.size());
        return users
                .stream()
                .map(CreateUser.ResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public CreateUser.ResponseDto get(@PathVariable long id) {
        log.info("Get user id={}", id);
        return userService
                .findUserByIdO(id)
                .map(CreateUser.ResponseDto::new)
                .get();
    }

    public static abstract class CreateUser {
        public static final class InputDto {
            public final String email;
            public final String login;

            public InputDto(String email, String login) {
                this.email = email;
                this.login = login;
            }

            public UserToCreate asUserToCreate() {
                return new UserToCreate(email, login);
            }
        }

        public static final class ResponseDto {
            public final long id;
            public final String email;
            public final String login;

            public ResponseDto(User user) {
                this.id = user.getId();
                this.email = user.getEmail();
                this.login = user.getLogin();
            }
        }
    }
}
