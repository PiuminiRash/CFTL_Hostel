package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SalaryTm {
    private String StaffId;
    private String StaffName;
    private double SalaryAmt;
    private double Bonus;
    private double E;
    private double FinalSalary;
}
