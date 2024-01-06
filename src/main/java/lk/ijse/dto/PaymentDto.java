package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDto {
    private String month;
    private String Date;
    private String StudentId;
    //private String StudentName;
    private double Amt;
}
