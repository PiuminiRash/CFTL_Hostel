package lk.ijse.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StaffDto implements Serializable {
    private String StaffType;
    private String StaffId;
    private String StaffName;
    private int ContactNo;
    private String NIC;
    private String Email;
}
