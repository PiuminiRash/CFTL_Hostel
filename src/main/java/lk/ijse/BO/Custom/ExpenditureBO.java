package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.ExpenditureDto;
import lk.ijse.dto.tm.ExpenditureTm;

import java.sql.SQLException;
import java.util.List;

public interface ExpenditureBO extends SuperBO {
    boolean saveExpenditure(ExpenditureDto dto) throws SQLException, ClassNotFoundException;
    List<ExpenditureTm> searchExpenditure(int year, String month) throws SQLException;
}
