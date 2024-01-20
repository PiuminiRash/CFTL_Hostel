package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.StaffDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Room;
import lk.ijse.Entity.Staff;
import lk.ijse.dto.RoomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDAOImpl implements StaffDAO {
    @Override
    public ArrayList<Staff> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Staff entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Staff VALUES (?,?,?,?,?,?)",
                entity.getStaffType(),entity.getStaffId(),entity.getStaffName(),entity.getContactNo(),entity.getNIC(),entity.getEmail());
    }

    @Override
    public boolean update(Staff entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Staff SET StaffType = ? , StaffName = ? , ContactNo = ? , NIC = ? , Email = ? WHERE StaffId = ?",
                entity.getStaffType(),entity.getStaffName(),entity.getContactNo(),entity.getNIC(),entity.getEmail(),entity.getStaffId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Staff WHERE  StaffId= ?" , id );
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Staff search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Staff WHERE StaffId = ?",id);
        if (resultSet.next()) {
            return new Staff(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }else {
            return null;
        }
    }
}
