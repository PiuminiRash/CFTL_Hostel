package lk.ijse.DAO.Custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Income;

import java.sql.SQLException;

public interface IncomeDAO extends CrudDAO<Income> {
    public Income search(String type, String month, String year) throws SQLException, ClassNotFoundException;
}