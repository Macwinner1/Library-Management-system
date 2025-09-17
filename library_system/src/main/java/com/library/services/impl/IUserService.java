package main.java.com.library.services.impl;

import main.java.com.library.data.models.User;
import main.java.com.library.data.repository.BookRepository;
import main.java.com.library.data.repository.UserRepository;
import main.java.com.library.dto.User.UserDto;
import main.java.com.library.dto.User.request.LoginRequestDto;
import main.java.com.library.dto.User.request.RegisterRequestDto;
import main.java.com.library.exception.UserAlreadyExistsException;
import main.java.com.library.exception.UsernameNotFoundException;
import main.java.com.library.services.UserServices;
import main.java.com.library.utils.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class IUserService implements UserServices {


    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BCrypt bCrypt = new BCrypt();
    private final UserMapper userMapper = new UserMapper();

    @Autowired
    public IUserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public UserDto registerUser(RegisterRequestDto userData) {
        if(checkUserExists(userData.getUserName(), userData.getEmail())) {
            throw new UserAlreadyExistsException("A user with this Username or email already exists.");
        }
        User user = UserMapper.mapToUser(userData, new User(), bCrypt);
        userRepository.save(user);
        return UserMapper.mapToUserDto(user);

    }

    @Override
    public UserDto loginUser(LoginRequestDto userDto){
        User user = userRepository.findByUsername(userDto.getUserName()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + userDto.getUserName())
        );
        boolean passwordMatch = BCrypt.checkpw(user.getPassword(), userDto.getPassword());
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email).isPresent();
    }
}
