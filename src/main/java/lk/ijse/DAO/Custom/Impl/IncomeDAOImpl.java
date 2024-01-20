package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.IncomeDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Income;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeDAOImpl implements IncomeDAO {
    @Override
    public ArrayList<Income> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Income entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO income (Description,Amount,Year,Month,Date) VALUES(?,?,?,?,?)",
                entity.getDesc(),entity.getAmount(),entity.getYear(),entity.getMonth(),entity.getDate());
    }

    @Override
    public boolean update(Income dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Income search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public Income search(String type,String month,String year) throws SQLException , ClassNotFoundException {
        return null;
    }
}
