package lk.ijse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Attendance {
    private String date;
    private String teacherId;
    private boolean isPresent;
}
