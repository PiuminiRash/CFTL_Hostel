package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SalaryAttendanceDto {
    private String TeacherId;
    private String TeacherName;
    private double SalaryAmt;
    private double AttendanceCount;
    private double finalSalary;
}
