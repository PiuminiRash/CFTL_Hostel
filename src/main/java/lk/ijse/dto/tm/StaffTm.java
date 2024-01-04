package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StaffTm {
    private String staffId;
    private String staffName;
    private String email;
    private Button delete;
    private RadioButton select;

    public StaffTm (String staffId,String staffName,String email,Button delete) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.email = email;
        this.delete = delete;
    }

    public StaffTm (String staffId,String staffName,RadioButton select) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.select = select;
    }
}
