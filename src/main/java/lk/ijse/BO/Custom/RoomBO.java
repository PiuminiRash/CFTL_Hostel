package lk.ijse.BO.Custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.Entity.Room;
import lk.ijse.dto.RoomDto;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDto dto) throws SQLException, ClassNotFoundException;
    boolean updateRoom(RoomDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;
    List<RoomDto> getAllRoom() throws SQLException;
    List<RoomDto> getIncomplete() throws SQLException;
    RoomDto searchRoom(String id) throws SQLException, ClassNotFoundException;
}
