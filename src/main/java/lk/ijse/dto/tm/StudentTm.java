package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentTm {
    private String StudentId;
    private String StudentName;
    private String StudentAddress;
    private String Section;
    private Button UpdateBtn;
    private Button DeleteBtn;

    public StudentTm (String studentId, String studentName,String studentAddress,String section, javafx.scene.control.Button update,javafx.scene.control.Button delete) {
        this.StudentId = studentId;
        this.StudentName = studentName;
        this.StudentAddress = studentAddress;
        this.Section = section;
        this.UpdateBtn = update;
        this.DeleteBtn = delete;
    }
}

