package lk.ijse.dto;

import lk.ijse.dto.tm.AddTeacherTm;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PlaceSubjectDto {
    private String SectionName;
    private String Bucket;
    private String SubjectCode;
    private String SubjectName;
    private List<AddTeacherTm> addTeacherTmList = new ArrayList<>();
}
