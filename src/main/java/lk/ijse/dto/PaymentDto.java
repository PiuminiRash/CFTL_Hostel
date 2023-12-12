package lk.ijse.dto;

import lombok.*;

import java.time.Month;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDto {
    private java.time.Month Month;
    private String Date;
    private String StudentId;
    private String StudentName;
    private double Amt;
}
