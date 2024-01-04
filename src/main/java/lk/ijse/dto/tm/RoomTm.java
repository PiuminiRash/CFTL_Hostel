package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class RoomTm {
    private String RoomNo;
    private String RoomName;
    private int NoOfBed;
    private int StudentCount;
    private Button Delete;
    private String Complete;
}
