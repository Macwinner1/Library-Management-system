package main.java.com.library.services;

import jakarta.servlet.http.HttpSession;
import main.java.com.library.data.models.Book;
import main.java.com.library.data.models.User;
import main.java.com.library.dto.User.UserDto;
import main.java.com.library.dto.User.request.LoginRequestDto;
import main.java.com.library.dto.User.request.RegisterRequestDto;

import java.util.Optional;


public interface UserServices {

    boolean checkUserExists(String userName, String email);

    UserDto loginUser(LoginRequestDto userDto);

    UserDto registerUser(RegisterRequestDto userData);

    User getAuthenticatedUser(HttpSession session);

}
