package lk.ijse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Payment {
    private String month;
    private String Date;
    private String StudentId;
    private double Amt;
}
