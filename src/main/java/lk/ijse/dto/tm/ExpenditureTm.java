package lk.ijse.dto.tm;

import lk.ijse.dto.IncomeDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ExpenditureTm extends IncomeDto {
    private String date;
    private String desc;
    private double amount2;
}
