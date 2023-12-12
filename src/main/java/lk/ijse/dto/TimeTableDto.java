package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TimeTableDto {
    private String section;
    private String mon;
    private String thu;
    private String wen;
    private String tue;
    private String fri;
    private String sat;
    private String sun;
}
