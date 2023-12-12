package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class RoomDto {
    private String RoomNo;
    private String RoomName;
    private int NoOfBed;
    private int StudentCount;
}
