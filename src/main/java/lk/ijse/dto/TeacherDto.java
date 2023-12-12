package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeacherDto {
    private String TeacherId;
    private String TeacherName;
    private String Address;
    private String NIC;
}
