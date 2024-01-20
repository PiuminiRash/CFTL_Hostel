package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.SubjectDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface SubjectDetailsBO extends SuperBO {
    boolean saveSubjectDetails(List<SubjectDetailsDto> subjectDetailsDtoList) throws SQLException, ClassNotFoundException;
}
