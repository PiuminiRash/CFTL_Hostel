package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SalaryDto {
    private String Type;
    private String Month;
    private String StaffId;
    private String StaffName;
    private double SalaryAmt;
    private double Bonus;
    private double E;
    private double finalSalary;
}
