package lk.ijse.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Expenditure {
    private String desc;
    private double amount;
    private int year;
    private String month;
    private String date;
}
