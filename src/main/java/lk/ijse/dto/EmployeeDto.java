package lk.ijse.dto;

import lombok.*;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDto {
    private String EmployeeId;
    private String EmployeeName;
    private String Address;
    private String JobType;
   // private JobTypeDto jobTypeDto;
    private String NIC;

}
