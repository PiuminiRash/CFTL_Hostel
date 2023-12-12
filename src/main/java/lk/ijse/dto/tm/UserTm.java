package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserTm {
    private String userName;
    private String password;
    private String email;
    private Button btn;
}
