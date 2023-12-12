package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserDto {
    private String userName;
    private String password;
    private String email;
}
