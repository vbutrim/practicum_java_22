package com.vbutrim.filmorate.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User save(UserToCreate user) {
        String sqlQuery = "INSERT INTO USERS (EMAIL, LOGIN) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
                    stmt.setString(1, user.getEmail());
                    stmt.setString(2, user.getLogin());
                    return stmt;
                },
                keyHolder);

        return user.asCreated(
                Objects.requireNonNull(keyHolder.getKey()).longValue()
        );
    }

    public Optional<User> findUserByIdO(long id) {
        final String sqlQuery = "SELECT * FROM USERS WHERE id = ?";
        final List<User> users = jdbcTemplate.query(sqlQuery, UserDao::cons, id);

        if (users.isEmpty()) {
            return Optional.empty();
        } else if (users.size() > 1) {
            throw new IllegalStateException();
        } else {
            return Optional.of(users.get(0));
        }
    }

    public void update(User user) {
        String sqlQuery = "UPDATE users SET email = ?, login = ?  WHERE id = ?";
        jdbcTemplate.update(
                sqlQuery,
                user.getEmail(),
                user.getLogin(),
                user.getId()
        );
    }

    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", UserDao::cons);
    }

    private static User cons(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("email"),
                rs.getString("login")
        );
    }
}