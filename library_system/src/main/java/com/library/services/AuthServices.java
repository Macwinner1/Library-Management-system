package main.java.com.library.services;

import jakarta.servlet.http.HttpSession;
import main.java.com.library.data.models.User;

import java.util.Optional;

public interface AuthServices {

   Optional<User> getAuthenticatedUser(HttpSession session);
}
