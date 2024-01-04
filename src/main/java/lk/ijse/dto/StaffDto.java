package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StaffDto {
    private String StaffType;
    private String StaffId;
    private String StaffName;
    private int ContactNo;
    private String NIC;
    private String Email;
}
