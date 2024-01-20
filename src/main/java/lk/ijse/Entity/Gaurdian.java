package lk.ijse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Gaurdian {
    private String studentId;
    private String gardianName;
    private String email;
    private String contactNo;

    public Gaurdian(String studentId) {
        this.studentId = studentId;
    }
}
