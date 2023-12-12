package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentTm {
    private String date;
    private String studentId;
    private String studentName;
    private double amt;
   // private Button remove;
}
