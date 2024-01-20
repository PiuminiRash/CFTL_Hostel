package lk.ijse.DAO.Custom.Impl;

import lk.ijse.DAO.Custom.RoomDAO;
import lk.ijse.DAO.SQLUtil;
import lk.ijse.Entity.Room;
import lk.ijse.dto.RoomDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Room");
        ArrayList<Room> getAllRoom = new ArrayList<>();
        while (resultSet.next()) {
            Room entity = new Room(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3));
            getAllRoom.add(entity);
        }
        return getAllRoom;
    }

    @Override
    public boolean save(Room entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO room VALUES(?,?,?)",
                entity.getRoomNo(),entity.getRoomName(),entity.getNoOfBed());
    }

    @Override
    public boolean update(Room entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Room SET RoomName = ?, NoOfBeds = ? WHERE RoomNo = ?",
                entity.getRoomName(),entity.getNoOfBed(),entity.getRoomNo());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Room WHERE RoomNo = ?" , id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Room search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Room WHERE RoomNo = ?" , id);
        if (resultSet.next()) {
            return new Room(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        } else {
            return null;
        }
    }
}
