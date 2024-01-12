package lk.ijse.dto;

import lk.ijse.dto.tm.SectionTm;
import lk.ijse.dto.tm.StaffTm;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PlaceSubjectDto {
    private String SubjectCode;
    private String SubjectName;
    private String Bucket;
    private List<SectionTm> sectionTmList = new ArrayList<>();
    private List<StaffTm> staffTmList = new ArrayList<>();
}
