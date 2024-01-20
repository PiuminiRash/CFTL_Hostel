package lk.ijse.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class RoomDto implements Serializable {
    private String RoomNo;
    private String RoomName;
    private int NoOfBed;
    //private int StudentCount;
}
