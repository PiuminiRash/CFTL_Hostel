package lk.ijse.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentDto {
    private String StudentId;
    private String StudentName;
    private String StudentAddress;
    private String Section;
    private String Bucket01;
    private String Bucket02;
    private String Bucket03;
    private String RoomNo;
}
