package main.java.com.library.dto.User.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import main.java.com.library.dto.BaseResponseDto;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserResponseDto extends BaseResponseDto {

    public UserResponseDto(HttpStatusCode statusCode, String statusMessage){
        super(statusCode,statusMessage);
    }

}
