package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SubjectStudentTm {
    private String StudentId;
    private String StudentName;
    private String Section;
    private String Bucket1;
    private String Bucket2;
    private String Bucket3;
}
