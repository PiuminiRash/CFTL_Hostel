package lk.ijse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Staff {
    private String StaffType;
    private String StaffId;
    private String StaffName;
    private int ContactNo;
    private String NIC;
    private String Email;
}
