package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.AttendanceDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Attendance;
import lk.ijse.Entity.Room;
import lk.ijse.dto.RoomDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<Attendance> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Attendance entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Attendance (Date, TeacherId) VALUES (?, ?)",
                entity.getDate(),entity.getTeacherId(),entity.isPresent());
    }

    @Override
    public boolean update(Attendance dto) throws SQLException, ClassNotFoundException {
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
    public Attendance search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
