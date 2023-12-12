package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class AttendanceDto {
    private String date;
    private String teacherId;
    private String teacherName;
    private boolean isPresent;
}
