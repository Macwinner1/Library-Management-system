package main.java.com.library.services;


import main.java.com.library.dto.User.UserDto;
import main.java.com.library.dto.User.request.LoginRequestDto;
import main.java.com.library.dto.User.request.RegisterRequestDto;

public interface UserServices {

    boolean checkUserExists(String userName, String email);

    UserDto loginUser(LoginRequestDto userDto);

    UserDto registerUser(RegisterRequestDto userData);

}
