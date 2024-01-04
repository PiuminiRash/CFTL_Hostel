package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SectionTm {
    private String SectionName;
    private int StudentCount;
    private Button Delete;
    private RadioButton Select;

    public SectionTm(String SectionName,int StudentCount,Button Delete) {
        this.SectionName = SectionName;
        this.StudentCount = StudentCount;
        this.Delete = Delete;
    }

    public SectionTm(String SectionName,RadioButton Select) {
        this.SectionName = SectionName;
        this.Select = Select;
    }
}
