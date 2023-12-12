package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTm {
    private String EmployeeId;
    private String EmployeeName;
    private String Address;
    private String JobType;
    private String NIC;
    private Button Delete;

    public EmployeeTm (String employeeId, String employeeName,String address,String jobType,String NIC,javafx.scene.control.Button delete) {
        this.EmployeeId = employeeId;
        this.EmployeeName = employeeName;
        this.Address = address;
        this.JobType = jobType;
        this.NIC = NIC;
        this.Delete = delete;
    }
}
