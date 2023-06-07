package com.vbutrim.filmorate.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private final long id;
    private final String email;
    private final String login;
}
