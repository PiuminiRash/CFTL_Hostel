package lk.ijse.dto.tm;

import lombok.*;
import javafx.scene.control.Button;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SubjectTm {
    private String SubjectCode;
    private String SubjectName;
    private String Section;
    private String Bucket;
    //private Button Update;
    private Button Delete;
}
