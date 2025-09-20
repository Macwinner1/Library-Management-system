package main.java.com.library.services.impl;

import jakarta.servlet.http.HttpSession;
import main.java.com.library.data.models.User;
import main.java.com.library.exception.UserNotAuthenticatedException;
import main.java.com.library.services.AuthServices;
import main.java.com.library.services.UserServices;

import java.util.Optional;

public class IAuthServices implements AuthServices {
    
    UserServices userServices;

    public Optional<User> getAuthenticatedUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserNotAuthenticatedException("User not authenticated");
        }
        return Optional.of(user);
    }
}
