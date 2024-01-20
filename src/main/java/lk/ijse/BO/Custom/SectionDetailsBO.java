package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.SectionDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface SectionDetailsBO extends SuperBO {
    boolean saveSectionDetails(List<SectionDetailsDto> sectionDetailsDtoList) throws SQLException, ClassNotFoundException;
}
