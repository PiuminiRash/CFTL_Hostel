package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class AddTeacherTm {
    private String TeacherId;
    private String TeacherName;
    private Button Btn;

    public AddTeacherTm(String TeacherId, String TeacherName ,javafx.scene.control.Button btn) {
        this.TeacherId = TeacherId;
        this.TeacherName = TeacherName;
        this.Btn = btn;
    }
}
