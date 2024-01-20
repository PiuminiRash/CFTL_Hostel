package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Entity.Gaurdian;
import lk.ijse.dto.GardianDto;

import java.sql.SQLException;

public interface GaurdianBO extends SuperBO {
    boolean saveGardian(GardianDto dto) throws SQLException, ClassNotFoundException;
    boolean updateGardian(GardianDto dto) throws SQLException, ClassNotFoundException;
    GardianDto searchGuardian(String id) throws SQLException, ClassNotFoundException;
}
