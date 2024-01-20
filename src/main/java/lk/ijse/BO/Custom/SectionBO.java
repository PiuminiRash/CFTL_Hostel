package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.SectionsDto;

import java.sql.SQLException;
import java.util.List;

public interface SectionBO extends SuperBO {
    List<SectionsDto> getAllSections() throws SQLException;
    SectionsDto searchSection(String name) throws SQLException;
}
