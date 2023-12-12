package lk.ijse.dto.tm;

import lombok.*;
import javafx.scene.control.Button;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherTm {
    private String TeacherId;
    private String TeacherName;
    private String Address;
    private String NIC;
    private Button Update;
    private Button Remove;

    public TeacherTm (String teacherId, String teacherName,String address,String NIC, javafx.scene.control.Button update,javafx.scene.control.Button remove) {
        this.TeacherId = teacherId;
        this.TeacherName = teacherName;
        this.Address = address;
        this.NIC = NIC;
        this.Update = update;
        this.Remove = remove;
    }
}
