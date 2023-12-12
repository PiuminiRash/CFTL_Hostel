package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class AttendanceTm {
    /*private String TeacherId;
    private String TeacherName;
    private Button RemoveBtn;

    public AttendanceTm(String teacherId,String teacherName,javafx.scene.control.Button removeBtn){
        this.TeacherId = teacherId;
        this.TeacherName = teacherName;
        this.RemoveBtn = removeBtn;
    }*/
    private String teacherId;
    private String teacherName;
    private Button removeBtn;
    private boolean isPresent;

    public AttendanceTm(String teacherId, String teacherName, Button removeBtn) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.removeBtn = removeBtn;
    }
}
