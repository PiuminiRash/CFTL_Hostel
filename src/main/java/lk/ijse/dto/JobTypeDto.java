package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class JobTypeDto {
    private String JobCode;
    private String JobType;
    private String  Description;
}
