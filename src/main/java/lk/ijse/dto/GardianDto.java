package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class GardianDto {
    private String studentId;
    private String gardianName;
    private String email;
    private String contactNo;
}
