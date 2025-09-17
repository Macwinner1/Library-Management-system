package main.java.com.library.dto.User.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LoginRequestDto {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "The length of the username should be atleast 4")
    public String UserName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should be minimum of 8 characters")
    public String Password;
}
