package com.vbutrim.filmorate.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User save(UserToCreate userToCreate) {
        return userDao.save(userToCreate);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public Optional<User> findUserByIdO(long id) {
        return userDao.findUserByIdO(id);
    }
}
