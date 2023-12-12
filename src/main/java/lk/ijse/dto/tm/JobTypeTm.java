package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobTypeTm {
    private String JobCode;
    private String JobType;
    private String DayPayment;
    private Button Remove;
}
