package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.IncomeDto;
import lk.ijse.dto.tm.IncomeTm;

import java.sql.SQLException;
import java.util.List;

public interface IncomeBO extends SuperBO {
    boolean saveIncome(IncomeDto dto) throws SQLException, ClassNotFoundException;
    List<IncomeTm> searchIncome(int year, String month) throws SQLException;

}
