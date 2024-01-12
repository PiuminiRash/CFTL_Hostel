package lk.ijse.dto;

import lk.ijse.dto.tm.StaffTm;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SubjectDetailsDto {
    private String SubjectCode;
    private String StaffId;
}
