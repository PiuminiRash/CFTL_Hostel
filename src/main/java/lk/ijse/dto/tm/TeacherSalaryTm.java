package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TeacherSalaryTm {
    private String TeacherId;
    public String TeacherName;
    public double datPay;
    public double attendanceCount;
    public double salary;
}
