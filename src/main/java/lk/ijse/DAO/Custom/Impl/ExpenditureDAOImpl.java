package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.ExpenditureDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Expenditure;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenditureDAOImpl implements ExpenditureDAO {
    @Override
    public ArrayList<Expenditure> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Expenditure entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO expenditure (Description,Amount,Year,Month,Date) VALUES(?,?,?,?,?)",
                entity.getDesc(),entity.getAmount(),entity.getYear(),entity.getMonth(),entity.getDate());
    }

    @Override
    public boolean update(Expenditure dto) throws SQLException, ClassNotFoundException {
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
    public Expenditure search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
