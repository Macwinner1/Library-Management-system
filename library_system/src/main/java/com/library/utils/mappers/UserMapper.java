package main.java.com.library.utils.mappers;

import main.java.com.library.data.models.Role;
import main.java.com.library.data.models.User;
import main.java.com.library.dto.User.UserDto;
import main.java.com.library.dto.User.request.RegisterRequestDto;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public class UserMapper {

    public static UserDto mapToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserId(user.getUserId());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    public static User mapToUser(
            UserDto userDto
    ){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        return user;
    }

    public static User mapToUser(
            RegisterRequestDto userDto, User user, BCrypt bCrypt
    ){

        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12)));
        user.setRole(Role.USER);
        return user;
    }
}
