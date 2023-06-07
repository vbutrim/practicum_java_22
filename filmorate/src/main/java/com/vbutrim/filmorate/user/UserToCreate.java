package com.vbutrim.filmorate.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserToCreate {
    private final String email;
    private final String login;

    public User asCreated(long id) {
        return new User(
                id,
                email,
                login
        );
    }
}
