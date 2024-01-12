package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ExamDto {
    private String Year;
    private String Month;
    private String SubjectCode;
    private String StudentId;
    private String Mark;
}
