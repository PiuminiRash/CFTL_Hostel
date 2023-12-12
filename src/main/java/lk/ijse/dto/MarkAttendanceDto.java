package lk.ijse.dto;

import lk.ijse.dto.tm.AttendanceTm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MarkAttendanceDto {
    private LocalDate date;
    private String desc;
    private List<AttendanceTm> employeeAttendanceTmList = new ArrayList<>();

}
