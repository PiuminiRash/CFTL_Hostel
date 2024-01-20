package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.GaurdianDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Gaurdian;
import lk.ijse.dto.GardianDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GaurdianDAOImpl implements GaurdianDAO {
    @Override
    public ArrayList<Gaurdian> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Gaurdian entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Gardian VALUES (?, ?, ?, ?)",
                entity.getStudentId(),entity.getGardianName(),entity.getEmail(),entity.getContactNo());
    }

    @Override
    public boolean update(Gaurdian entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Gardian SET GardianName =? , Email = ? , ContactNo = ? WHERE StudentId = ? ",
                entity.getGardianName(),entity.getEmail(),entity.getContactNo(),entity.getStudentId());
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
    public Gaurdian search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Gardian WHERE StudentId = ?",id);
        if (resultSet.next()) {
            return new Gaurdian(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;

    }
}
